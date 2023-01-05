<template>
<div class="container table-responsive scrollTableau ">
  <table class="table table-striped table-bordered ">
   <thead>
    <tr>
    <th class="text-center align-middle">NOM </th>
    <th class="text-center align-middle">PRENOM</th>
    <th class="text-center align-middle">PROMOTION </th>
    <th class="text-center align-middle">TYPE </th>
    <th class="text-center align-middle">DESTINATION</th>
    <th class="text-center align-middle">DEPART</th>
    <th class="text-center align-middle">PERIODE</th>
    <th class="text-center align-middle">DUREE EN MOIS</th>
    <th class="text-center align-middle">ETAT</th>
    <th class="text-center align-middle">ACTIONS</th>
    </tr>
  </thead>
  <tbody >
    <tr v-for="(mobilite,index) of mobilites" :key=index>
    <td class="text-center align-middle">{{mobilite[1].nom}}</td>
    <td class="text-center align-middle">{{mobilite[1].prenom}}</td>
    <td class="text-center align-middle">{{mobilite[1].promo}}</td>
    <td class="text-center align-middle">{{mobilite[2].typeMobilite}}</td>
    <td class="text-center align-middle">{{mobilite[2].nomEtablissementAccueil}} ({{mobilite[2].pays}})</td>
    <td class="text-center align-middle">{{mobilite[0].dateDepart}}</td>
    <td class="text-center align-middle">{{mobilite[0].periode}}</td>
    <td class="text-center align-middle">{{mobilite[0].dureeEnMois}}</td>
    <td class="text-center align-middle">{{mobilite[3]}}</td>
    <td class="text-center align-middle"> 
        <div class="text-center d-flex flex-row w-75 justify-content-around m-auto align-middle">
           
        <a @click="$emit('set',mobilite)" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn" type="button" > <img v-bind:src="addDocument" alt="edit"></a>
        
        <a @click="$emit('delete',mobilite[0].id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
        </div> 
    </td>
    </tr>
    
    
  </tbody>
</table>

</div>
</template>

<script setup>
   
    import poubelle from '../../../../img/poubelle.png'
    import addDocument from '../../../../img/ajouterUnDocument.png'
    
    defineProps(['mobilites'])
</script>

<style>

    thead{
        font-family: 'Bebas Neue', sans-serif;
        color: #f3f3f3;
        background-color: #022e51;
        font-size: 1.3em;
    }
</style>