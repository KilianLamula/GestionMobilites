<template>
    <div id="form">
        <form @submit.prevent="searchMobilite()">
            <div class="form-group formRecherche">
                <input id="searchMobilite" class="form-control w-75" name="nom" type="text" v-model="mobilite" placeholder="Rechercher un étudiant..."/>
                <input id="boutonRecherche" type="submit" value="Rechercher" />
            </div>
            
        </form>
    </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const emit = defineEmits(['searchMobilite'])

const mobilite = ref('');

/**
 * fonction qui émet l'événement 'searchMobilite' vers le composant parent Mobilite, 
 * lui passant an paramètre l'entrée saisie par l'utilisateur
 */
function searchMobilite(){
    emit('searchMobilite',mobilite.value)
}
</script>

<style>
    #searchMobilite{
    background-color: #A3B4C859;
    border-radius: 10px;
    border: 0px;
    padding-top: 5px;
    padding-bottom: 5px;
    font-family: 'Bebas Neue', sans-serif;
    font-size: 1.2em;
    }

    #boutonRecherche{
        border-radius: 10px;
        background-color: #022e51;
        color:#f3f3f3;
        
    }
</style>