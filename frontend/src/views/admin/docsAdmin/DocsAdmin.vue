<template>
<div class=" mt-5 container ">
  <div class="row h-25 align-items-center">
    <div class="col-4"></div>

    <h1 class="col-4 text-center">Gestion des documents administratifs</h1>
    
    <div class="col-4 text-right">
      <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#ajout">
        <img img v-bind:src="ajoutDoc" alt="ajouter document" class="mr-1">
        Ajouter un document
      </button>
    </div>
    <FormAddDocsAdmin @post="addDoc" @changeFile="setFile"/>

    <TableDocsAdmin @update="setDoc" 
    @updateFile="setFile" 
    @updateDoc="updateDoc" 
    @delete="deleteDocument" 
    v-bind:documents="listeDocuments"
    class="mt-4"/>
  </div>
</div>
</template>

<script setup>
import TableDocsAdmin from "./tableDocsAdmin/TableDocsAdmin.vue";
import FormModifDocsAdmin from './formModifDocsAdmin/FormModifDocsAdmin.vue'
import FormAddDocsAdmin from './formAddDocsAdmin/FormAddDocsAdmin.vue'
import { onMounted, onUpdated } from "vue";
import { reactive,ref } from 'vue';

import ajoutDoc from '../../../img/addDoc.png'

//navabr active
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("docNav").classList.add("active")

//Liste de documents
const listeDocuments = reactive([]);
let file = ref("")
let idDoc = ref(0)

/**
 * 
 * @param
 * @return
 * Fonction qui remplit la liste listeDocuments
 */
function getDoc(){
  const fetchOptions = {method:"GET"};
  fetch("/api/documents/",fetchOptions)
  .then((response) => {return response.json()})
  .then((dataJSON) => {
  listeDocuments.splice(0,listeDocuments.length)
  dataJSON._embedded.documents.forEach((d)=>listeDocuments.push(d))
  console.log(listeDocuments)
  })
  .catch((error) => console.log(error));
}

/**
 * 
 * @param id
 * @return
 * Fonction qui permet de supprimer un document à partir de son id
 */
function deleteDocument(id){
  let url = `http://localhost:8989/api/documents/`
  
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {method: "DELETE", headers: myHeaders};
  fetch(url+`${id}`,fetchOptions)
  .then(()=>{getDoc()})
  .catch((error)=>{console.log(error)})
}

/**
 * 
 * @param doc
 * @return
 * Fonction qui modifie les données
 */
function setDoc(doc){
  console.log(doc)
  idDoc.value = doc.id
 
  document.getElementById("updateIntitule").value = doc.intitule
  document.getElementById("updateDescription").value = doc.description
  file.value=doc.fichier
}

function updateDoc(event){
  event.preventDefault()
  let intitule = document.getElementById("updateIntitule").value 
  let description = document.getElementById("updateDescription").value

  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {  method:"PUT", 
                                    headers: myHeaders, 
                                    body: JSON.stringify({
                                        intitule:intitule,
                                        description:description,
                                        fichier: file.value
                                        })};
  fetch(`/api/documents/${idDoc.value}`,fetchOptions)
  .then(()=>{
    getDoc()
  })   
  .catch((err)=>{ console.log("erreur: ",err)}) 

}

/**
 * 
 * @param event 
 * evenement du @change
 * Fonction qui convertis un fichier pdf en string base64
 */

function setFile(event){
  
  
  let reader = new FileReader();
  reader.onloadend = function() {
        
        file.value=reader.result
        console.log(file.value)
      }
  reader.readAsDataURL(event.target.files[0]);

}



function addDoc(event){
  event.preventDefault()
  const urlPost = `/api/documents
`
  let intitule = document.getElementById("addIntitule").value
  let desc = document.getElementById("addDescription").value
  

  let myHeaders = new Headers();

  myHeaders.append("Content-Type", "application/json");

  const fetchOptions = {method:"POST", headers: myHeaders, body: JSON.stringify({
    intitule:intitule, 
    description:desc,
    fichier:file.value})};

  fetch(urlPost,fetchOptions)
  .then((response) => getDoc())
  .catch((error) => console.log(error));
}

onMounted(() => {
  getDoc()
});
</script>

<style>

</style>