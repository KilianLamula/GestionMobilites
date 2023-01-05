<template>
<div id="form">
    <form @submit.prevent="search()">
        <div class="form-group formRecherche">
            <input id="search" class="form-control w-75" name="nom" type="text" v-model="etud" placeholder="Rechercher un étudiant..."/>
            <input id="boutonRecherche" type="submit" value="Rechercher" />
        </div>
        
    </form>
</div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const emit = defineEmits(['searchEtud'])

const etud = ref('');

function search(){
    emit('searchEtud',etud._value)
}
</script>

<style>

    #search{
    background-color: #A3B4C859;
    border-radius: 10px;
    border: 0px;
    padding-top: 5px;
    padding-bottom: 5px;
    font-family: 'Bebas Neue', sans-serif;
    font-size: 1.2em;
    }

    .formRecherche{
        display: flex;
        justify-content: space-between;
        font-family: 'Bebas Neue', sans-serif;
    }

    #boutonRecherche{
        border-radius: 10px;
        background-color: #022e51;
        color:#f3f3f3;
    }

</style>