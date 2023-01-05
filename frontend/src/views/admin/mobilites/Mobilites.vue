<template>
  <div class=" mt-5 container ">
  <div class="row h-25  align-items-center">

    <div class="col-4 h-50  d-flex flex-column justify-content-around align-items-left">
    <SearchMobilite v-bind:mobilites="listeMobilitesTab" @searchMobilite="searchMobilite"/>
    <FiltreMobilites @searchByPromo="searchByPromo" @searchByDestination="searchByDestination" @searchByEtatMobilite="searchByEtatMobilite" />
    </div>

    <h1 class="col-4 text-center">GESTION DES MOBILITES</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#exempleModalM">
            <img img v-bind:src="globe" alt="globe" class="mr-1">
            Ajouter une mobilité
        </button>
    </div>

  <TableMobilites @set="setMobilite" @delete="deleteMobilite" v-bind:mobilites="listeMobilitesTab" class="mt-4"/>

  </div>
  
  <FormAddMobilites @ajouter="addMobilite" />
  </div>
  
</template>

<script setup>

    import {reactive, onMounted} from 'vue'
    import TableMobilites from './tableMobilites/TableMobilites.vue'
    import FormAddMobilites from './formAddMobilite/FormAddMobilite.vue';
    import SearchMobilite from './searchMobilite/SearchMobilite.vue'
    import FiltreMobilites from './filtreMobilites/FiltreMobilites.vue'
    import globe from '../../../img/globe.png'

    //navabr active
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("mobilitesNav").classList.add("active")

    const listeMobilites =  reactive([])
    const listeMobilitesTab = reactive([])


   function dateDiff(date1, duree){
                var diff = {}  
                
                let dateRetour = new Date(date1)
                console.log("date depart",duree)         
                          // Initialisation du retour
                console.log(dateRetour.setMonth(dateRetour.getMonth()+ 5))
                console.log("date retour",dateRetour) 
                let actuelDate= new Date()
                
                var tmp = dateRetour - actuelDate;
            
                tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
                diff.sec = tmp % 60;                    // Extraction du nombre de secondes
            
                tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
                diff.min = tmp % 60;                    // Extraction du nombre de minutes
            
                tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
                diff.hour = tmp % 24;                   // Extraction du nombre d'heures
                
                tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
                diff.day = tmp;
                console.log(diff)
                return diff.day;
    }
  const urlAllMobilites = `http://localhost:8989/api/mobilites`

  function getMobilites(url){
        
        listeMobilites.splice(0,listeMobilites.length)         //On vide la liste des destinations avant de la remplir afin d'éviter les doublons
        listeMobilitesTab.splice(0,listeMobilitesTab.length) 

        
        fetch(url)
        .then((res)=>{return res.json()})
        .then((json)=>{
       
           
            let dateActuel = new Date()
            
           
            for(let d of json._embedded.mobilites){
                  console.log(d)
                  
                  fetch(d._links.etudiant.href)
                  .then((res)=>{return res.json()})
                  .then((json)=>{
                    return json
                  })
                  .then((etudiant)=>{
                    console.log(etudiant)
                    fetch(d._links.destination.href)
                    .then((res)=>{return res.json()})
                    .then((destination)=>{
                        return destination
                      })
                    .then((destination)=>{
                    
                    if(dateActuel.getTime()<new Date(d.dateDepart).getTime()){
                      
                      listeMobilites.push([d,etudiant,destination,"Non Validée"])
                      listeMobilitesTab.push([d,etudiant,destination,"Non Validée"])
                    }
                    else{
                      if(dateDiff(d.dateDepart,d.dureeEnMois)<0){
                          
                          listeMobilites.push([d,etudiant,destination,"Validée"])
                          listeMobilitesTab.push([d,etudiant,destination,"Validée"])
                      }
                      else{
                        
                        listeMobilites.push([d,etudiant,destination,"En cours"])
                        listeMobilitesTab.push([d,etudiant,destination,"En cours"])
                      }}
                    })
                  })  
            }
        })
  }

    function deleteMobilite(id){
        console.log('delete',id)
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const fetchOptions = {
                method: "DELETE",
                headers: myHeaders
                };
        let url = `http://localhost:8989/api/mobilites/${id}`
        fetch(url,fetchOptions)
        .then(()=>{getMobilites(urlAllMobilites)})
        .catch((err)=>{
            window.alert("Vous ne pouvez pas supprimer une destination qui est liée à une ou plusieurs mobilités")
            console.log("message d'erreur : ",err)})
    }
    

    function setMobilite(mobilite){
        document.getElementById("nomEtablissement").value=destination[0].nomEtablissementAccueil
        document.getElementById("nomVille").value=destination[0].ville
        document.getElementById("nomPays").value=destination[0].pays
        document.getElementById(`${destination[0].typeMobilite}`).selected = true
        document.getElementById("semestres").value= parseInt(destination[0].nbPlaceSemestre)
        document.getElementById("nbPlaceAnnee").value=parseInt(destination[0].nbPlaceAnnee)
        document.getElementById("dateFinContrat").value=destination[0].dateFinDeContratIsis

        document.getElementById("btnSub").addEventListener('click',()=>{
            console.log(destination)
            updateMobilite(destination)
        })
    }

    function updateMobilite(mobilite){

           let nomEtablissement = document.getElementById("nomEtablissement").value
           let nomVille = document.getElementById("nomVille").value
           let nomPays = document.getElementById("nomPays").value
           let typeMobilite = document.getElementById("typeMobilite").value
           let nbPlaceSemestre = document.getElementById("semestres").value
           let nbPlaceAnnee= document.getElementById("nbPlaceAnnee").value
           let date = document.getElementById("dateFinContrat").value

            const url = `http://localhost:8989/api/destinations/${destination[0].id}` // l’url de l'API

            let myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");
            const fetchOptions = {  method:"PUT", 
                                    headers: myHeaders, 
                                    body: JSON.stringify({
                                        nomEtablissementAccueil:nomEtablissement,
                                        dateFinDeContratIsis:date,
                                        nbPlaceAnnee:nbPlaceAnnee,
                                        nbPlaceSemestre:nbPlaceSemestre,
                                        typeMobilite:typeMobilite,
                                        ville:nomVille,
                                        pays:nomPays
                                        })};
            fetch(url,fetchOptions)
            .catch((error) => console.log(error));

    }
  
    function addMobilite() {
    
    
    const url = `http://localhost:8989/api/` // l’url de l'API
    let idDestination = document.getElementById("addDestination").value
    let idEtudiant = document.getElementById("selectEtud").value
  
    let date = document.getElementById("addDepart").value
    let duree = document.getElementById("addDuree").value
    let periode = document.getElementById("periode").value
    
      
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {  method:"POST", 
                            headers: myHeaders, 
                            body: JSON.stringify({
                                dateDepart:date,
                                dureeEnMois:duree,
                                periode:periode,
                                destination:`http://localhost:8989/api/destinations/${idDestination}`,
                                etudiant:`http://localhost:8989/api/etudiants/${idEtudiant}`,
                                retourExperience:""

                                })};
    console.log("fetchOptions : ",fetchOptions.body)
    fetch("http://localhost:8989/api/mobilites",fetchOptions)
    .then(()=>{getMobilites()})
    .catch((error) =>{ 
      console.log(error)
      window.alert(date)});
  }

function getMobilitesFiltrees(url){      
        listeMobilitesTab.splice(0,listeMobilitesTab.length) //On vide la liste des mobilités avant de la remplir afin d'éviter les doublons

        fetch(url)
        .then((response)=>{return response.json()})
        .then((dataJSON)=>{
           
            let dateActuel = new Date()

            dataJSON.forEach((mobilite)=>{ // on parcourt toutes les mobilités de la base de données
              
              let etud = mobilite.etudiant //on récupère l'étudiant associé à chaque mobilité
              let dest = mobilite.destination //on récupère la destination associé à chaque mobilité

              if(dateActuel.getTime()<new Date(mobilite.dateDepart).getTime()){ 
                //si la date actuelle se situe avant la date de départ, l'état de la mobilité est "Non Validée"
                listeMobilitesTab.push([mobilite,etud,dest,"Non Validée"])
              } else if (dateDiff(mobilite.dateDepart,mobilite.dureeEnMois)<0) { //si la mobilité est terminée
                listeMobilitesTab.push([mobilite,etud,dest,"Validée"])
              } else { //si la mobilité est en cours
                listeMobilitesTab.push([mobilite,etud,dest,"En Cours"])
              }
            })
        })
  }

/**
 * @param promo sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les mobilités concernant les étudiants de la promotion sélectionnée
 */
function searchByPromo(promo){
  const url = `/api/mobilites/findByPromo?promo=${promo}` //url permettant d'accéder aux mobilités filtrées par promo

  if(promo != 'tous'){ //si on sélectionne n'importe quelle promo de la liste déroulante, on filtre

    getMobilitesFiltrees(url) //on récupère les mobilités filtrées

  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités

    getMobilites(urlAllMobilites)
  }
}

/**
 * @param destination sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les mobilités associées à la destination sélectionnée
 */
function searchByDestination(idDestination){

  const url = `/api/mobilites/findByIdDestination?id=${idDestination}` //url permettant d'accéder aux mobilités filtrées par destination
  
  if(idDestination != '0'){ //si on sélectionne n'importe quelle destination de la liste déroulante, on filtre
    getMobilitesFiltrees(url) //on récupère les mobilités filtrées
  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités
    getMobilites(urlAllMobilites)
  }
}

/**
 * @param etat sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les mobilités associées à un état de mobilité
 */
function searchByEtatMobilite(etat){
  
  const url = `/api/mobilites/findByEtatMobilite?etat=${etat}` //url permettant d'accéder aux mobilitées filtrées par leur état

  if (etat != 'tous'){ //si on sélectionne n'importe quel état de la liste déroulante, on filtre
    getMobilitesFiltrees(url)
  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités
    getMobilites(urlAllMobilites)
  }
}

/**
 * @param etudiant : nom ou prénom d'un étudiant saisi par l'utilisateur
 * fonction qui permet de récupérer toutes les mobilités concernant un étudiant saisi
 */
function searchMobilite(etudiant){
  const url = `/api/mobilites/findByEtudiant?etudiant=${etudiant}` //url permettant d'accéder aux mobilités filtrées par promo

  console.log('test')
  if(etudiant != ''){ //si on sélectionne n'importe quelle promo de la liste déroulante, on filtre

    getMobilitesFiltrees(url) //on récupère les mobilités filtrées

  } else { // on sélectionne l'option permettant d'afficher toutes les mobilités

    getMobilites(urlAllMobilites)
  }
}

onMounted(()=>{
    getMobilites(urlAllMobilites)
    
})
</script>

<style>
</style>