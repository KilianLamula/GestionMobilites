<template>
<div class="container table-responsive scrollTableau ">
  <table class="table table-striped  table-bordered ">
   <thead>
    <tr id="enteteTabDest">
    <th class="text-center align-middle">NOM ETABLISSEMENT </th>
    <th class="text-center align-middle">VILLE </th>
    <th class="text-center align-middle">PAYS </th>
    <th class="text-center align-middle">TYPE MOBILITE </th>
    <th class="text-center align-middle">NB PLACES </th>
    <th class="text-center align-middle">STATUT CONTRAT </th>
    <th class="text-center align-middle">ACTIONS</th>
    </tr>
  </thead>
  <tbody >
    <tr v-for="(destination,index) of destinations" :key=index >
    <td class="text-center align-middle ">{{destination[0].nomEtablissementAccueil}}</td>
    <td class="text-center align-middle ">{{destination[0].ville}}</td>
    <td class="text-center align-middle ">{{destination[0].pays}}</td>
    <td class="text-center align-middle ">{{destination[0].typeMobilite}}</td>
    <td style="d-flex  align-middle">
        <div class="text-center ">{{destination[0].nbPlaceAnnee}}/an </div>
        <div class="text-center ">{{destination[0].nbPlaceSemestre}}/semestre </div>
    </td>
    <td class="text-center  align-middle">{{destination[1]}}</td>
    <td class="text-center"> 
        <div class="text-center d-flex flex-row w-75 justify-content-around m-auto align-middle">
           
        <a @click="$emit('set',destination)" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
        
        <a @click="$emit('delete',destination[0].id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
        </div> 
    </td>
    </tr>
    
    
  </tbody>
</table>


<!-- Modal -->
<FormModifDestination @updateImage="updateImage" @updateDestination="updateDestination"/>
</div>
</template>

<script setup>

    import {defineEmits} from 'vue'
   
    import poubelle from '../../../../img/poubelle.png'
    import edit from '../../../../img/edit.png'
    import FormModifDestination from '../formModifDestination/FormModifDestination.vue'

    defineProps(['destinations'])
    const emits = defineEmits(['updateImage','updateDestination'])
    function updateImage(event){
        emits('updateImage',event)
    }

    function updateDestination(event){
        
        emits('updateDestination',event)

    }
</script>

<style>

    thead{
        font-family: 'Bebas Neue', sans-serif;
        color: white;
        background-color: #022e51;
        font-size: 1.3em;
    }
</style>