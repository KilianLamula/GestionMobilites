<template>
<div class="modal fade " id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">MODIFIER UNE DESTINATION</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form class="container-fluid" >
        <div class="modal-body">
          <div class="w-75 row m-auto " >
            <div class="mb-3 row">
                <div class="col">
                <label for="nomEtablissement" class="form-label">NOM DE L'ETABLISSEMENT:</label>
                <input type="text" class="form-control " id="nomEtablissement">
                </div>
            </div>

            <div class="row mb-3">
              <div class="col-6 ">
                  <label for="nomVille" class="form-label">VILLE:</label>
                  <input type="text" class="form-control w-75 " name="nomVille" id="nomVille">
              </div>
              <div class="col-6 d-flex flex-row justify-content-end ">
                  <div class=" w-75">
                  <label for="nomPays" class="form-label">PAYS:</label>
                  <input type="text" class="form-control " name="nomPays" id="nomPays">
                  </div>
              </div>
            </div>

            <div class="mb-3 row">
                <div class="col">
                  <label for="typeMobilite">TYPE DE MOBILITE:</label>
                  <select id="typeMobilite" name="typeMobilite" class="form-select">
                    <option id="ETUDE" value="ETUDE">Etude</option>
                    <option id="STAGE" value="STAGE">Stage</option>
                    <option id="HUMANITAIRE" value="HUMANITAIRE">Humanitaire</option>
                  </select>
                </div>
            </div>

            <div class="mb-3 row">
              <div class="col">
                <label for="semestres" class="form-label">NOMBRE DE PLACE PAR SEMESTRE:</label>
                <input type="number" min="0" class="form-control" name="semestres" id="semestres">
              </div>
            </div>

            <div class="mb-3 row">
                <div class="col">
                <label for="nbPlaceAnnee" class="form-label">NOMBRE DE PLACE PAR ANNEE:</label>
                <input type="number" min="0" class="form-control " name="nbPlaceAnnee" id="nbPlaceAnnee">
                </div>
            </div>

            <div class="mb-3 row">
                <div class="col">
                <label for="dateFinContrat" class="form-label">DATE DE FIN DE CONTRAT:</label>
                <input type="date"  class="form-control " name="dateFinContrat" id="dateFinContrat">
                </div>
            </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="image" class="form-label">CHOISIR/MODIFIER L'IMAGE:</label>
                    <input type="file" @change="changeImage"  class="form-control " name="image" id="imageModifier">
                    </div>
                </div>
          
          </div>
        </div>         
        <div class="modal-footer">
        <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
        <button type="submit" id="btnSub" @click="updateDestination" data-bs-dismiss="modal" class="btnOrange">Modifier</button>
      </div>
    </form>
      
      
    </div>
  </div>
</div>
</template>

<script setup>


import {  defineEmits } from 'vue';

const emits = defineEmits(['updateImage','updateDestination'])

function changeImage(event){
  emits('updateImage', event )
  
}

function updateDestination(event){
  
    emits('updateDestination',event)

}

</script>

<style>
#nomEtablissement, #nomVille, #nomPays, #typeMobilite, #semestres, #nbPlaceAnnee, #dateFinContrat, #image{
 background: rgba(163, 180, 200, 0.35); 
}
#Label{
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
    background: rgba(163, 180, 200, 0.35);
    border-radius: 10px;
    font-family: 'Montserrat';
    font-weight: 400;
    font-size: 20px;
    color: #022E51;
}
#exampleModal{
    border-radius: 10px;
}
</style>
