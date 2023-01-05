<template>
<div class="container table-responsive scrollTableau">
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th class="text-center align-middle">Nom du document</th>
                <th class="text-center align-middle">Description</th>
                <th class="text-center align-middle">Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(document) in documents" :key=[document.id] class="align-self-center" >
            <td class="text-center align-middle">{{document.intitule}}</td>
            <td class="text-center align-middle">{{document.description}}</td>
            <td class="text-center align-middle"> 
                <div class="text-center d-flex justify-content-around w-75 m-auto align-middle">
                <a @click="$emit('update',document)" data-bs-toggle="modal" data-bs-target="#modif" class="btn" type="button" > <img v-bind:src="edit" alt="edit"></a>
                <a @click="$emit('delete',document.id)" class="btn" type="button"> <img v-bind:src="poubelle" alt="trash"></a>
                </div> 
            </td>
            </tr>
        </tbody>
    </table>

    <!-- Modal -->
    <FormModifDocsAdmin @updateFile="updateFile" @updateDoc="updateDoc"/>
</div>
</template>

<script setup>
    import FormModifDocsAdmin from '../../docsAdmin/formModifDocsAdmin/FormModifDocsAdmin.vue'
    import edit from '../../../../img/edit2.png'
    import poubelle from '../../../../img/poubelle.png'
    import { defineEmits} from 'vue'
import { emit } from 'process';


    defineProps(['documents'])
    const emits = defineEmits(['updateFile','updateDoc'])
    function updateFile(event){
            emits('updateFile',event)

    }

    function updateDoc(event){
        emits('updateDoc',event)

    }
</script>

<style>
th{
    text-transform: uppercase;
}
</style>