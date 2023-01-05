<template>
<div class="h-100 container ">
    <div class="row h-25 align-items-center">
        <h1 class="text-center">DOCUMENTS ADMINISTRATIFS</h1>
    </div>
    <div id="docAdmin" class=" row   ">
        <div id="docAdmin" class="col-12 ">
            <div class="row mt-3 overflow-auto mt-0 mb-5 g-3 g-xl-5 text-center ">    
                <Carte  @oppen="setDoc" v-for="(document) in listeDocuments" :key=[document.id]  v-bind:document="document" v-bind:chemin="doc"/>
            </div>
        </div>
    </div>
    <PopUp v-bind:document="docu"/>
</div>
</template>

<script setup>
import Carte from "../../../components/CarteDocsAdminEtud.vue"
import doc from "./img/img_docsadmin.png"
import PopUp from './popUpDocsAdmin/PopUpDocsAdmin.vue'

import { onMounted, reactive,ref } from "vue";


//navabr active
let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
for(let l of listeNav){
document.getElementById(l).classList.remove("active")
}
document.getElementById("docNav").classList.add("active")

const listeDocuments = reactive([]);
let docu = ref({})

/**
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
 * @param doc
 * Fonction qui permet de récupérer le document de la carte sur laquelle on clique
 */
function setDoc(doc){
    docu.value = doc
    console.log(document)
}

/**
 * Lorsqu'on crée le composant FiltreDestinations, on exécute la fonction getDoc()
 */
onMounted(()=>{
    getDoc()
    console.log(listeDocuments)
})
</script>

<style>

</style>