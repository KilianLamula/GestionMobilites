<template>
<div class="modal fade" id="modif" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Modifier le document</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="form">
            <form @submit.prevent="updateDoc">
                <div class="form-group">
                    <label for="updateIntitule" class="font-weight-bold">Nom du document :</label>
                    <input id="updateIntitule" class="form-control" name="updateIntitule" type="text" v-model="intitule" placeholder="Entrez le nom du document ..." required/>
                </div>
                <div class="form-group">
                    <label for="updateDescription" class="font-weight-bold">Desciption :</label>
                    <input id="updateDescription" class="form-control" name="updateDescription" type="text" v-model="description" placeholder="Entrez une description du document ..." required/>
                </div>
                <input id="idDocToEdit" style="display: none;"/>
                <!--Upload de fichier :-->
                <div>
                  <form method="POST" enctype="multipart/form-data" action="/upload">
                    <div class="form-group">
                      <label for="file" class="font-weight-bold">Fichier à télécharger :</label>
                      <input class="form-control" @change="majFile" id="updateFile" type="file" required name="file" />
                    </div>
                  </form>
                </div>
              
                <!---->
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input id="btnSub" type="submit" @click="majDoc" class="btnOrange" value="Modifier" data-bs-dismiss="modal"/>
                </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>

import {defineEmits} from 'vue'
const emits = defineEmits(['updateFile','updateDoc'])
function majFile(event){
    emits('updateFile',event)
}

function majDoc(event){
    emits('updateDoc',event)
}

</script>

<style scoped>
h5{
    font-size: 30px;
    line-height: 36px;
}
label{
    font-size: 24px;
    line-height: 29px;
}
h5,label{
    text-transform: uppercase;
    color: #022E51;
    font-family: 'Bebas Neue';
    font-weight: 400;
    font-style: normal;
    text-align: center;
}
.form-control{
    border-radius: 10px;
    font-family: 'Montserrat';
    font-weight: 400;
    font-size: 20px;
    color: #022E51;
    background-color: rgba(163, 180, 200, 0.35);
;
}
#description, #intitule{
  background: rgba(163, 180, 200, 0.35);
}
#modif{
    border-radius: 10px;
}
</style>