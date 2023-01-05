<template>
    <div class="filtresEtudiants w-100 row m-auto py-2">
        <div class="col text-center">
            <select id="promotion" class="col text-center formSelect" @change="searchByPromo">
                <option id="toutesPromo" :value="0">PROMOTION</option>
                <option v-for="promo of data.promotions" :value="promo" :key="promo">{{promo}}</option>
            </select>
        </div>
        <div class=" col text-center">
            <select id="etat_mobilite" class="col text-center formSelect" @change="searchByEtatMobilite">
                <option id="tousEtats" value="tous">Etat mobilité</option>
                <option value="val">Validée</option>
                <option value="nVal">Non validée</option>
            </select>
        </div>

    </div>

</template>

<script setup>
import { onMounted, reactive } from "@vue/runtime-core";
import {defineEmits} from 'vue'

    //on definit les evenements
    const emit = defineEmits(['searchByEtatMobilite', 'searchByPromo'])

    //liste des promotions
    let data = reactive(
        {
            promotions:[]
        }
    )

    //Pour récupérer toutes les promotions existantes
    function getPromotions(){
        fetch('/api/etudiant/promo', {method: 'GET'})
        .then((result)=>
            result.json()
        )
        .then((dataJson)=>{
            data.promotions = dataJson
        })
    }

    onMounted(()=>{
        getPromotions()
    })

    //Pour envoyer un evenement au parent en fonction de l'etat de la mobilité choisie
    function searchByEtatMobilite(event){
        emit('searchByEtatMobilite', event.target.value)
        //on désactive le filtre état de la mobilité si le filtre promotion est activé
        document.getElementById('toutesPromo').selected = true;
    }

    //Pour envoyer un evenement au parent en fonction de la promotion
    function searchByPromo(event){
        emit('searchByPromo', event.target.value)
        //on désactive le filtre promotion si le filtre état de la mobilité est activé
        document.getElementById('tousEtats').selected = true;
    }


</script>

<style>

.filtresEtudiants{
    font-family: 'Bebas Neue', sans-serif;
    background-color: #A3B4C859;
    border-radius: 10px;
    color: #022e51;
}

select{
    font-family: 'Bebas Neue', sans-serif;
}

.formSelect{
    background-color: transparent;
    border: none;
    color: #022e51;
    font-size: 1.2em;
}

.formSelect option{
    background-color: rgb(227, 231, 243);
}

</style>