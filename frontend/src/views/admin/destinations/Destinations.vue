<template>
  <div class=" mt-5 container ">
  <div class="row h-25  align-items-center">

    <div class="col-4 h-50 d-flex flex-column">
      <SearchDestination v-bind:destinations="listeDestinationsTab" @searchDestination="searchDestination"/>
      
      <FiltreDestinations @searchByPays="searchByPays" @searchByTypeMobilite="searchByTypeMobilite" @searchByStatutContrat="searchByStatutContrat"/>
      
    </div>

    <h1 class="col-4 text-center">GESTION DES DESTINATIONS</h1>

    <div class="col-4 text-right">
        <button type="button" class="btnOrange " data-bs-toggle="modal" data-bs-target="#exempleModalD">
            <img img v-bind:src="world" alt="world" class="mr-1">
            Ajouter une destination
        </button>
    </div>

  <TableDestinations 
              @set="setDestination" 
              @delete="deleteDestination" 
              v-bind:destinations="listeDestinationsTab" 
              @updateImage="encodeImageFileAsURL"
              @updateDestination="updateDestination"
              class="mt-4"/>
  </div>
  <FormAddDestination @ajouter="postDestination" @changePicture="encodeImageFileAsURL" />
  </div>
  
</template>

<script setup>

    import {reactive, onMounted,ref} from 'vue'
    import TableDestinations from './tableDestinations/TableDestinations.vue'
    import FormAddDestination from './formAddDestination/FormAddDestination.vue'
    import FiltreDestinations from './filtreDestinations/FiltreDestinations.vue'
    import SearchDestination from './searchDestination/SearchDestination.vue'

    import world from '../../../img/world.png'

    //navabr active
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("destinationsNav").classList.add("active")
   
    const listeDestinationsTab = reactive([])
    let img = ref("")
    let idDestination = ref(0)
    const url = 'api/destinations'
    function dateDiff(date1, date2){
                var diff = {}                           // Initialisation du retour
                var tmp = date2 - date1;
                
                tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
                diff.sec = tmp % 60;                    // Extraction du nombre de secondes
            
                tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie enti??re)
                diff.min = tmp % 60;                    // Extraction du nombre de minutes
            
                tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (enti??res)
                diff.hour = tmp % 24;                   // Extraction du nombre d'heures
                
                tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
                diff.day = tmp;
                console.log(diff)
                return diff;
    }

    const urlAllDestinations = '/api/destinations';
    function getDestinations(url){
           
        listeDestinationsTab.splice(0,listeDestinationsTab.length) //On vide la liste des destinations avant de la remplir afin d'??viter les doublons

        fetch(url)
        .then((res)=>{return res.json()})
        .then((json)=>{
            let isValide;
            let date;
            let actuelDate = new Date();
        
            console.log(actuelDate)
            for(let d of json._embedded.destinations){

                    date=new Date(d.dateFinDeContratIsis)
                    
                    if(dateDiff(actuelDate,date).day<365 && dateDiff(actuelDate,date).day>0 ){
                                isValide = "Bient??t Expir??"
                    }
                    else if(dateDiff(actuelDate,date).day<0){
                        isValide = "Expir??"
                    }
                    else{
                        isValide = "Valide"
                    }
                   
                    listeDestinationsTab.push([d,isValide])
                    

            }
        })
    }

    function deleteDestination(id){
        console.log('delete',id)
        let myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        const fetchOptions = {
                method: "DELETE",
                headers: myHeaders
                };
        let url = `/api/destinations/${id}`
        fetch(url,fetchOptions)
        .then(()=>{getDestinations(urlAllDestinations)})
        .catch((err)=>{
            window.alert("Vous ne pouvez pas supprimer une destination qui est li??e ?? une ou plusieurs mobilit??s")
            console.log("message d'erreur : ",err)})
    }
    

    function setDestination(destination){

        idDestination.value = destination[0].id

        document.getElementById("nomEtablissement").value=destination[0].nomEtablissementAccueil
        document.getElementById("nomVille").value=destination[0].ville
        document.getElementById("nomPays").value=destination[0].pays
        document.getElementById(`${destination[0].typeMobilite}`).selected = true
        document.getElementById("semestres").value= parseInt(destination[0].nbPlaceSemestre)
        document.getElementById("nbPlaceAnnee").value=parseInt(destination[0].nbPlaceAnnee)
        document.getElementById("dateFinContrat").value=destination[0].dateFinDeContratIsis
        img.value=destination[0].image
        
    
      }

    function updateDestination(event){
          console.log("cc")
           event.preventDefault()
           alert(document.getElementById("nomEtablissement").value)
           let nomEtablissement = document.getElementById("nomEtablissement").value
           let nomVille = document.getElementById("nomVille").value
           let nomPays = document.getElementById("nomPays").value
           let typeMobilite = document.getElementById("typeMobilite").value
           let nbPlaceSemestre = document.getElementById("semestres").value
           let nbPlaceAnnee= document.getElementById("nbPlaceAnnee").value
           let date = document.getElementById("dateFinContrat").value

            const url = `/api/destinations/${idDestination.value}` // l???url de l'API

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
                                        pays:nomPays,
                                        image:img.value
                                        })};
            fetch(url,fetchOptions)
            .then(()=>{getDestinations(urlAllDestinations)})
            .catch((error) => console.log(error));

    }

    
    function encodeImageFileAsURL(event) {
        
        let file = event.target.files[0];
        let reader = new FileReader();
        reader.onloadend = function() {
          
          img.value=reader.result
          console.log(img.value)
        }
        reader.readAsDataURL(file);

      }
    
    function postDestination(event){
        event.preventDefault()
        let nomEtablissement = document.getElementById("addNomEtablissement").value
        let nomVille = document.getElementById("addNomVille").value
        let nomPays = document.getElementById("addNomPays").value
        let typeMobilite = document.getElementById("addTypeMobilite").value
        let nbPlaceSemestre = document.getElementById("addSemestres").value
        let nbPlaceAnnee= document.getElementById("addNbPlaceAnnee").value
        let date = document.getElementById("addDateFinContrat").value
        

            if(nomEtablissement==""|| nomVille==""||nomPays==""||date==""){
            window.alert("Veuillez remplir les 4 premiers champs et la date!")
            
    }
    else{
    const url = `/api/destinations` // l???url de l'API

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {  method:"POST", 
                            headers: myHeaders, 
                            body: JSON.stringify({
                                nomEtablissementAccueil:nomEtablissement,
                                dateFinDeContratIsis:date,
                                nbPlaceAnnee:nbPlaceAnnee,
                                nbPlaceSemestre:nbPlaceSemestre,
                                typeMobilite:typeMobilite,
                                ville:nomVille,
                                pays:nomPays,
                                image:img.value
                                
                                })};
    fetch(url,fetchOptions)
    .then(()=>{getDestinations(urlAllDestinations)})
    .catch((error) => console.log(error));
    }
    }
   
/**
 * @param pays s??lectionn?? dans la liste d??roulante
 * fonction qui permet de r??cup??rer toutes les destinations dans le pays s??lectionn??
 */
function searchByPays(pays){

  const fetchOptions = { method: "GET" }; //on utilise l'op??ration GET car on veut r??cup??rer les destinations filtr??es par pays
  const url = '/api/destinations/search/findByPays?pays='+pays //url permettant d'acc??der aux destinations filtr??es par pays

  if(pays != 'tous'){ //si on s??lectionne n'importe quel pays de la liste d??roulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON) => {
          getDestinations(url)
      })
      .catch((error) => console.log(error));
  } else { // on s??lectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * @param type s??lectionn?? dans la liste d??roulante
 * fonction qui permet de r??cup??rer toutes les destinations associ??es ?? un type de mobilit?? s??lectionn??
 */
function searchByTypeMobilite(type){

  const fetchOptions = { method: "GET" }; //on utilise l'op??ration GET car on veut r??cup??rer les destinations filtr??es par type de mobilit??
  const url = '/api/destinations/search/findByTypeMobilite?type='+type //url permettant d'acc??der aux destinations filtr??es par type de mobilit??

  if(type != 'tous'){ //si on s??lectionne n'importe quel type de mobilit?? de la liste d??roulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON) => {
          getDestinations(url)

      })
      .catch((error) => console.log(error));
  } else { // on s??lectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * @param statut s??lectionn?? dans la liste d??roulante
 * fonction qui permet de r??cup??rer toutes les destinations associ??es ?? un statut de contrat s??lectionn??
 */
function searchByStatutContrat(statut){

  const fetchOptions = { method: "GET" }; //on utilise l'op??ration GET car on veut r??cup??rer les destinations filtr??es par statut de contrat
  const url = '/api/destinations/findByStatutContrat?statut='+statut //url permettant d'acc??der aux destinations filtr??es par statut de contrat

  if(statut != 'tous'){ //si on s??lectionne n'importe quel statut de la liste d??roulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON)=>{
        listeDestinationsTab.splice(0, listeDestinationsTab.length)
        dataJSON.forEach((destination)=>{
          let isValide;
          let date;
          let actuelDate = new Date();

          date=new Date(destination.dateFinDeContratIsis)
                    
          if(dateDiff(actuelDate,date).day<365 && dateDiff(actuelDate,date).day>0 ){
            isValide = "Bient??t Expir??"
          }
          else if(dateDiff(actuelDate,date).day<0){
            isValide = "Expir??"
          }
          else{
              isValide = "Valide"
          }
          listeDestinationsTab.push([destination,isValide])
        })
      })
      .catch((error) => console.log(error));
  } else { // on s??lectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * Fonction qui filtre les destinations en fonction d'une cha??ne de caract??res saisie par l'utilisateur
 */

function searchDestination(inputUser){
  const url = `/api/destinations/search/findAllByNomEtablissementAccueilContainingIgnoreCaseOrVilleContainingIgnoreCase?nom=${inputUser}&ville=${inputUser}`
  fetch(url, {method: 'GET'})
  .then((result)=>{
    return result.json()
  })
  .then((dataJson)=>{
    getDestinations(url) //on r??cup??re les destinations filtr??es
  })
}

/**
 * lorsqu'on cr??e le composant Destinations, on ex??cute la fonction getDestinations(url) qui charge toutes les destinations de la BDD
 */
onMounted(()=>{ getDestinations(urlAllDestinations) })
</script>

<style>
</style>