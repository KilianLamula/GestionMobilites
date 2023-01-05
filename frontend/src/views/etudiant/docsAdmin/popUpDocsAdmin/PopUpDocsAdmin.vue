<template>
<div class="modal fade h-75 " id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg h-100">
    <div class="modal-content h-50">
      <div class="modal-header border-0">
      
        <h5 class="modal-title col-10 " >{{document.intitule}}</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body h-25 ">
          <div class="row h-50 overflow-y-auto">
              <div class="col-2 fw-bold ">Description : </div>
                <div class="col-10 text-left h-100 overflow-auto " style="word-wrap:break-word;">{{document.description}}</div>
          </div>

          <div class="row  h-25 align-items-center">
              <div class="col-4 fs-6 fw-bold ">Lien de telechargement : </div>
                <div  class="col-8 text-left h-100   align-items-center  ">
                    <a @click="download" id="btnDownLoad" type="button" class="text-center btn border   " >
                    Cliquez pour telecharger
                    </a>
                </div>
          </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>


import {  defineEmits } from 'vue';

const emits = defineEmits(['updateImage','updateDestination'])
const props = defineProps(['document'])

console.log(props.document.fichier)

/**
 * @param event
 * Fonction qui met à jour l'image
 */
function changeImage(event){
  emits('updateImage', event )
}

/**
 * @param event
 * Fonction qui met à jour la destination
 */
function updateDestination(event){
  emits('updateDestination',event)
}

/**
 * Fonction qui télécharge le fichier
 */
function download(){
  const linkSource = `${props.document.fichier}`;
  let btn = document.getElementById("btnDownLoad")

  const fileName = `${props.document.intitule}.pdf`
  btn.href = linkSource;
  btn.download = fileName;
  btn.click();
}
</script>

<style>
#exampleModal{
    border-radius: 10px;
    font-family: 'Montserrat', sans-serif;
}
h5{
  color: black;

}

</style>
