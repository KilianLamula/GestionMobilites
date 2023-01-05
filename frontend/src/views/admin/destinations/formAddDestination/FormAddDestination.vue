<template>
<div class="modal fade" id="exempleModalD" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
            <h5 class="modal-title font-weight-bold" id="Label">Ajouter une destination</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div id="form">
                <form >
                    <div class="modal-body">
                        <div class="w-75 row m-auto " >
                            <div class="mb-3 row">
                                <div class="col">
                                    <label for="nomEtablissement" class="form-label">NOM DE L'ETABLISSEMENT:</label>
                                    <input type="text" required class="form-control "  name="addNomEtablissement" id="addNomEtablissement" placeholder="Entrez le nom de l'établissement ...">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-6 ">
                                    <label for="nomVille" class="form-label">VILLE:</label>
                                    <input type="text" required class="form-control w-75 " name="addNomVille" id="addNomVille" placeholder="Entrez la ville ...">
                                </div>
                                <div class="col-6 d-flex flex-row justify-content-end ">
                                    <div class=" w-75">
                                        <label for="nomPays" class="form-label">PAYS:</label>
                                        <input type="text" required class="form-control " name="addNomPays" id="addNomPays" placeholder="Entrez le pays ...">
                                    </div>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="col">
                                    <label for="typeMobilite">TYPE DE MOBILITE:</label>
                                    <select id="addTypeMobilite"  name="addTypeMobilite" class="form-select">
                                            <option value="" disabled selected>Sélectionnez un type ...</option>
                                            <option id="ETUDE" value="ETUDE">Etude</option>
                                            <option id="STAGE" value="STAGE">Stage</option>
                                            <option id="HUMANITAIRE" value="HUMANITAIRE">Humanitaire</option>
                                    </select>
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="col">
                                    <label for="semestres" class="form-label">NOMBRE DE PLACES PAR SEMESTRE:</label>
                                    <input type="number"  min="0" class="form-control" name="addSemestres" id="addSemestres" placeholder="Entrez un chiffre ...">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <div class="col">
                                    <label for="nbPlaceAnnee" class="form-label">NOMBRE DE PLACES PAR ANNEE:</label>
                                    <input type="number"  min="0" class="form-control " name="addNbPlaceAnnee" id="addNbPlaceAnnee" placeholder="Entrez un chiffre ...">
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col">
                                    <label for="dateFinContrat" class="form-label">DATE DE FIN DE CONTRAT:</label>
                                    <input type="date"   class="form-control " name="addDateFinContrat" id="addDateFinContrat" placeholder="Entrez une date ...">
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col">
                                    <label for="image" class="form-label">CHOISIR L'IMAGE:</label>
                                    <input type="file"  @change="sendFile" class="form-control " name="addImage" id="addImage">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                        <input id="Bouton" @click="addDestination" data-bs-dismiss="modal" type="submit" class="btnOrange" value="Ajouter" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</template>

<script setup>
import { defineEmits} from 'vue'

const emit = defineEmits(['changePicture','ajouter'])

function sendFile(event){
    emit('changePicture',event)
}
function addDestination(event){
 
    emit('ajouter',event)
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
    background: rgba(163, 180, 200, 0.35);
    border-radius: 10px;
    font-family: 'Montserrat';
    font-weight: 400;
    font-size: 20px;
    color: #022E51;
}
#addTypeMobilite{
    background: rgba(163, 180, 200, 0.35);
}
#ajout{
    border-radius: 10px;
}
</style>