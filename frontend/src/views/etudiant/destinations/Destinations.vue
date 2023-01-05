<template>
<div class="mt-5 container">
    <div class="row h-25  align-items-center">
        <div class="col-3 h-50 d-flex flex-column">
            <FiltreDestinations @searchByPays="searchByPays" @searchByTypeMobilite="searchByTypeMobilite"/>
        </div>

        <div class="col-6 text-center">
            <h1 class="text-center">DÉCOUVREZ LES DESTINATIONS</h1>
        </div>

        <div class="col-3"></div>
    </div>
    <div class="row g-5 h-50 overflow-auto justify-content-center mt-3">
        <Carte @oppen="setDest" v-for="(destination,index) of listeDestinations" v-bind:destination="destination" v-bind:index="destination.id"  v-bind:nom="destination.nomEtablissementAccueil" v-bind:ville="destination.ville" v-bind:pays="destination.pays" v-bind:type="destination.typeMobilite" v-bind:img="destination.image"/>
    </div>
    <Popup v-bind:destination="desti" v-for="(destination,index) of listeDestinations" v-bind:index="destination.id" v-bind:img="destination.image" v-bind:type="destination.typeMobilite"/>
</div>
</template>

<script setup>
import {reactive,onMounted,ref} from 'vue'
import Carte from "../../../components/CarteDestinationsEtud.vue"
import Popup from "./popupInfo/PopupInfo.vue"
import FiltreDestinations from "./filtreDestinations/FiltreDestinations.vue"

const listeDestinations = reactive([])
let desti = ref({})

const urlAllDestinations = '/api/destinations';

/**
 * @param date1
 * @param date2
 * Fonction qui permet de vérifier si une mobilité ou un contrat est valide
 */
function dateDiff(date1, date2){
    var diff = {}                           // Initialisation du retour
    var tmp = date2 - date1;
    
    tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
    diff.sec = tmp % 60;                    // Extraction du nombre de secondes

    tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
    diff.min = tmp % 60;                    // Extraction du nombre de minutes

    tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
    diff.hour = tmp % 24;                   // Extraction du nombre d'heures
    
    tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
    diff.day = tmp;
    return diff;
}

/**
 * @param base64img
 * @param callback
 * Fonction qui convertit l'image en base 64 en image
 */
function Base64ToImage(base64img, callback) {
    var img = new Image();
    img.onload = function() {
        callback(img);
    };
    img.src = base64img;
}

/**
 * @param url
 * Fonction qui remplit la liste listeDestinations
 */
function getDestinations(url){
    listeDestinations.splice(0,listeDestinations.length) //On vide la liste des destinations avant de la remplir afin d'éviter les doublons

    fetch(url)
    .then((res)=>{return res.json()})
    .then((json)=>{
      
        let date;
        let actuelDate = new Date();
    
        for(let d of json._embedded.destinations){
            date=new Date(d.dateFinDeContratIsis)
            
            if(dateDiff(actuelDate,date).day>0){
                listeDestinations.push(d)
            }
        }
    })
}

/**
 * @param dest
 * Fonction qui permet de récupérer la destination de la carte sur laquelle on clique
 */
function setDest(dest){
    desti.value = dest
}

/**
 * @param pays sélectionné dans la liste déroulante
 * fonction qui permet de récupérer toutes les destinations dans le pays sélectionné
 */
function searchByPays(pays){

  const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer les destinations filtrées par pays
  const url = '/api/destinations/search/findByPays?pays='+pays //url permettant d'accéder aux destinations filtrées par pays

  if(pays != 'tous'){ //si on sélectionne n'importe quel pays de la liste déroulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON) => {
          getDestinations(url)
      })
      .catch((error) => console.log(error));
  } else { // on sélectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * @param type
 * fonction qui permet de récupérer toutes les destinations associées à un type de mobilité sélectionné
 */
function searchByTypeMobilite(type){

  const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer les destinations filtrées par type de mobilité
  const url = '/api/destinations/search/findByTypeMobilite?type='+type //url permettant d'accéder aux destinations filtrées par type de mobilité

  if(type != 'tous'){ //si on sélectionne n'importe quel type de mobilité de la liste déroulante, on filtre
    fetch(url, fetchOptions)
      .then((response) => { return response.json();})
      .then((dataJSON) => {
          getDestinations(url)

      })
      .catch((error) => console.log(error));
  } else { // on sélectionne l'option permettant d'afficher toutes les destinations
    getDestinations(urlAllDestinations)
  }
}

/**
 * lorsqu'on crée le composant Destinations, on exécute la fonction getDestinations(url) qui charge toutes les destinations de la BDD
 */
onMounted(()=>{ getDestinations(urlAllDestinations)})
</script>

<style>

</style>