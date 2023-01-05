<template>
  <div class="container table-responsive scrollTableau" >
    <table class="table table-striped  table-bordered">
        <thead>
          <tr>
            <th class="text-center align-middle">INE</th>
            <th class="text-center align-middle">Nom</th>
            <th class="text-center align-middle">Prenom</th>
            <th class="text-center align-middle">Promotion</th>
            <th class="text-center align-middle">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="etud in etudiants" :key="etud" class="align-self-center">
            <td class="text-center align-middle">{{etud.numEtud}}</td>
            <td class="text-center align-middle">{{etud.nom}}</td>
            <td class="text-center align-middle">{{etud.prenom}}</td>
            <td class="text-center align-middle">{{etud.promo}}</td>
            <td class="text-center align-middle">
              <div class="text-center d-flex justify-content-around w-75 m-auto align-middle">
              <a @click="$emit('update', etud)" data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
              <a @click="$emit('delete',etud.id)"  class="btn" type="button" ><img :src="Poubelle" alt="à la poubelle l'etudiant"/></a>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
</template>

<script setup>
    import { onMounted } from "@vue/runtime-core"
    import Poubelle from '../../../../img/poubelle.png'
    import edit from '../../../../img/edit.png'
    defineProps(["etudiants"])

  
</script>

<style scoped>

  th{
    font-size: 1.3em;
  }

</style>