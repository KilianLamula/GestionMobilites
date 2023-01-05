<template>
    <div class="filtresEtudiants w-100 row m-auto py-2">
        <div class="col px-0 text-center">
            <select id="paysSelector" class="formSelect" @change="searchByPays">
                <option value="tous" id="paysTous">PAYS</option>
                <option v-for="pays in listePays" :key="pays" :value="pays">{{pays}}</option>
            </select>
        </div>

        <div class="col px-0 text-center">
            <select id="type_mobilite" class="formSelect" @change="searchByTypeMobilite">
                <option value="tous" id="typesTous">TYPE MOBILITE</option>
                <option v-for="type in types" :key="type" :value="type">{{type}}</option>
            </select>
        </div>
    </div>
</template>

<script setup>

import {reactive, onMounted, defineEmits } from 'vue'

const listePays = reactive([]); // liste qui contient les différents pays des destinations
const types = reactive([]); // liste qui contient les différents types de mobilité des destinations

//on definit les evenements
const emit = defineEmits(['searchByPays', 'searchByTypeMobilite'])

/**
 * Lorsqu'on crée le composant FiltreDestinations, on exécute les fonctions recupererTousLesPays() et recupererTousTypesMobilite()
 */
onMounted(()=>{
        recupererTousLesPays()
        recupererTousTypesMobilite()
    })

/**
 * Fonction qui permet de récupérer les pays de toutes les destinations à partir de l'API
 */
function recupererTousLesPays(){

    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/destinations/pays', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            listePays.splice(0, listePays.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((pays) => {
            listePays.push(pays) })
        })
        .catch((error) => console.log(error));
}

/**
 * Fonction qui permet de récupérer les types de mobilité de toutes les destinations à partir de l'API
 */
function recupererTousTypesMobilite(){

    const fetchOptions = { method: "GET" }; //on utilise l'opération GET car on veut récupérer le pays des destinations
    fetch('/api/destinations/typesMobilite', fetchOptions)
        .then((response) => { return response.json(); })
        .then((dataJSON) => {
            types.splice(0, types.length) //on vide la liste avant de la reremplir
            dataJSON.forEach((type) => {
            types.push(type) 
            console.log(types)
            })
        })
        .catch((error) => console.log(error));
}

/**
 * @param event
 * Pour envoyer un evenement au parent en fonction du pays choisi
 */
function searchByPays(event){
    emit('searchByPays', event.target.value)
    //on désactive les 2 autres filtres si le filtre pays est activé
    document.getElementById('typesTous').selected = true; 
    document.getElementById('contratsTous').selected = true; 
}

/**
 * @param event
 * Pour envoyer un evenement au parent en fonction du type de mobilité
 */
function searchByTypeMobilite(event){
    emit('searchByTypeMobilite', event.target.value)
    //on désactive les 2 autres filtres si le filtre type de mobilité est activé
    document.getElementById('paysTous').selected = true; 
    document.getElementById('contratsTous').selected = true; 
}
</script>

<style>

    .dropdown-menu li:hover {
        background-color: #b74803;
        color: #f3f3f3;
        cursor: pointer;
    }

</style>