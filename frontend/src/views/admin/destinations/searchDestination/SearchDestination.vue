<template>
<div id="form">
    <form @submit.prevent="searchDestination()">
        <div class="form-group formRecherche">
            <input id="searchDestination" class="form-control w-75" name="nom" type="text" v-model="destination" placeholder="Rechercher un établissement, une ville..."/>
            <input id="boutonRecherche" type="submit" value="Rechercher" />
        </div>
        
    </form>
</div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const emit = defineEmits(['searchDestination'])

const destination = ref('');

/**
 * fonction qui émet l'événement 'searchDestination' vers le composant parent Destination, 
 * lui passant an paramètre l'entrée saisie par l'utilisateur
 */
function searchDestination(){
    emit('searchDestination',destination.value)
}
</script>

<style>

    #searchDestination{
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