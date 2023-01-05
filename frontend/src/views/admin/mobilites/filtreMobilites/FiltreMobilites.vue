<template>
    <div class="filtresEtudiants w-100 row m-auto py-2">
        <div class="row">
            <div class="col px-0 text-center">
                <select id="promoSelector" class="formSelect" @change="searchByPromo">
                    <option value="tous" id="promoTous">PROMOTION</option>
                    <option v-for="promo in listePromo" :key="promo" :value="promo">{{promo}}</option>
                </select>
            </div>

            <div class="col px-0  text-center">
                <select id="destination" class="formSelect" @change="searchByDestination">
                    <option value="0" id="destinationsTous">DESTINATION</option>
                    <option v-for="destination in listeDestinations" :key="destination" :value="destination.id">{{destination.nomEtablissementAccueil}} ({{destination.pays}})</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col px-0 ms-2">
                <select id="etat_mobilite" class="formSelect" @change="searchByEtatMobilite">
                    <option value="tous" id="etatsTous">ETAT MOBILITE</option>
                    <option v-for="etat in listeEtats" :key="etat" :value="etat">{{etat}}</option>
                </select>
            </div>
        </div>
        
    </div>
</template>

<script setup>

import { reactive, onMounted, defineEmits } from 'vue'

const listePromo = reactive([]); // liste qui contient les différents pays des destinations
const listeDestinations = reactive([]); // liste qui contient les différents types de mobilité des destinations
const listeEtats = reactive([]); // liste qui contient les différents statuts de contrat des destinations

//on definit les evenements
    const emit = defineEmits(['searchByPromo', 'searchByDestination', 'searchByEtatMobilite'])

onMounted(()=>{
        recupererToutesPromo()
        recupererToutesDestinations()
        recupererTousEtatsMobilites()
    })

/**
 * fonction qui permet de récupérer les promotions de toutes les mobilités à partir de l'API
*/
function recupererToutesPromo(){
    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer les promotions des mobilités
    fetch('/api/mobilites/promotions', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            listePromo.splice(0, listePromo.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((promo) => {
            listePromo.push(promo) })
        })
        .catch((error) => console.log(error));
    
}

/** 
 * fonction qui permet de récupérer les types de mobilité de toutes les destinations à partir de l'API
*/
function recupererToutesDestinations(){
    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/mobilites/destinations', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            listeDestinations.splice(0, listeDestinations.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((destination) => {
            listeDestinations.push(destination) 
            })
        })
        .catch((error) => console.log(error));
}

//fonction qui permet de récupérer les statuts des contrats de toutes les destinations à partir de l'API
function recupererTousEtatsMobilites(){
    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/mobilites/etatsMobilite', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            listeEtats.splice(0, listeEtats.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((etat) => {
            listeEtats.push(etat)
            })
        })
        .catch((error) => console.log(error))
}

//Pour envoyer un evenement au parent en fonction du pays choisi
function searchByPromo(event){
    emit('searchByPromo', event.target.value)
    //on désactive les 2 autres filtres si le filtre pays est activé
    document.getElementById('destinationsTous').selected = true; 
    document.getElementById('etatsTous').selected = true; 
}

//Pour envoyer un evenement au parent en fonction du type de mobilité
function searchByDestination(event){
    emit('searchByDestination', event.target.value)
    //on désactive les 2 autres filtres si le filtre type de mobilité est activé
    document.getElementById('promoTous').selected = true; 
    document.getElementById('etatsTous').selected = true; 
}

//Pour envoyer un evenement au parent en fonction du statut du contrat
function searchByEtatMobilite(event){
    emit('searchByEtatMobilite', event.target.value)
    //on désactive les 2 autres filtres si le filtre statut du contrat est activé
    document.getElementById('promoTous').selected = true; 
    document.getElementById('destinationsTous').selected = true;
}

</script>

<style>

</style>