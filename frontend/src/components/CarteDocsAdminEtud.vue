<template>
    <div class="col-12  col-xl-4 ">
        <a type="button" @click="oppenCard" data-bs-toggle="modal" data-bs-target="#exampleModal"  id="carte" class=" w-100 py-3 d-flex flex-column justify-content-center align-items-center border-0 ">
            <img v-bind:src="chemin" width="176" height="165" alt="image ">
            <div class="h4 mt-3"> {{document.intitule}}</div>
        </a>



    </div>
</template>

<script setup>
import {defineEmits} from 'vue'

const props = defineProps(["document","chemin"])
const emits = defineEmits(['oppen'])
function oppenCard(){
    emits('oppen',props.document)
    console.log(props.document)
}

</script>

<style>
#carte{
    background-color: #B74803;
    border-radius: 30px;
    color: #FFFFFF;
    font-family: "Bebas Neue";
    font-size: 24px;
    
}
.h4{
    text-align: center;
    padding-left: 4px;
    padding-right: 4px;
}
</style>