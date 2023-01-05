<template>
<div class="modal fade" id="ajout" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Ajouter un étudiant</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="form">
            <form>
                <div class="form-group">
                    <label for="nom" class="font-weight-bold">Nom :</label>
                    <input v-model="nom" id="nom" class="form-control" name="nom" type="text" placeholder="Entrez le nom de l'étudiant ..." required/>
                </div>
                <div class="form-group">
                    <label for="prenom" class="font-weight-bold">Prénom :</label>
                    <input v-model="prenom" id="prenom" class="form-control" name="prenom" type="text" placeholder="Entrez le prénom de l'étudiant ..." required/>
                </div>
                <div class="form-group">
                    <label for="ine" class="font-weight-bold">INE :</label>
                    <input v-model="ine" id="ine" class="form-control" name="numEtud" type="number" placeholder="Entrez l'INE de l'étudiant ..." required/>
                </div>
                <div class="form-group">
                    <label for="promotion" class="font-weight-bold">Promotion :</label>
                    <input v-model="promotion" id="promotion" class="form-control" name="promo" type="number" placeholder="Sélectionnez une promotion ..." required/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input @click="$emit('post', nom, prenom, promotion, ine)" id="Bouton" type="submit" class="btnOrange" value="Ajouter"  data-bs-dismiss="modal"/>
                 </div>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
    import {ref} from 'vue'

    let nom = ref('')
    let prenom = ref('')
    let promotion=ref()
    let ine = ref()

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
    background: rgba(163, 180, 200, 0.35);
    border-radius: 10px;
    font-family: 'Montserrat';
    font-weight: 400;
    font-size: 20px;
    color: #022E51;
}
#ajout{
    border-radius: 10px;
}
</style>