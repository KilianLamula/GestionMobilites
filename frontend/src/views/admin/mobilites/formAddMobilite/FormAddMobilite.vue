<template>
<div class="modal fade" id="exempleModalM" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title font-weight-bold" id="Label">Ajouter une mobilité</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
        <div id="form">
            <form >
                <div class="modal-body">
                <div class="w-75 row m-auto " >
                <div class="mb-3 row">
                    <div class="col-6">
                    <label for="selectPromo">PROMOTION:</label>
                    <select for="nomEtablissement" id="selectPromo" @change="setListeEtudiant($event)" name="selectPromo" class="form-select">
                        <option v-for="(promo,index) of listePromo[0]" v-bind:key=index id="{{promo}}"  v-bind:value="promo" >{{promo}}</option>
                    </select>
                    
                    </div>
                    <div class="col-6">
                    <label for="typeMobilite">TYPE DE MOBILITE:</label>
                    <select id="addTypeMobilite" @change="setListeDestinations($event)" name="addTypeMobilite" class="form-select">
                            <option id="ETUDE" value="ETUDE">Etude</option>
                            <option id="STAGE" value="STAGE">Stage</option>
                            <option id="HUMANITAIRE" value="HUMANITAIRE">Humanitaire</option>
                    </select>
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="col ">
                        <label for="selectEtud">ETUDIANT:</label>
                        <select for="nomEtablissement" id="selectEtud"  name="selectEtud" class="form-select">
                          <option v-bind:value="etudiant.id" v-for="(etudiant,index) of listeEtudiantsFiltres" v-bind:key=index id="{{etudiant.nom}}_{{etudiant.prenom}}" >{{etudiant.prenom}} {{etudiant.nom}}</option>
                        </select>
                </div>
                </div>
                <div class="mb-3 row">
                    <div class="col">
                    <label for="typeMobilite">DESTINATION:</label>
                    <select id="addDestination" name="addDestination" class="form-select">
                          <option  v-for="(destination,index) of listeDestinationsFiltres" v-bind:value="destination.id" v-bind:key=index  >{{destination.nomEtablissementAccueil}} </option>
                    </select>
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col-6">
                    <label for="periode" class="form-label">PERIODE:</label>
                    <select class="form-select" id="periode" name="periode">
                        <option value="SEMESTRE1">Semestre 1</option>
                        <option value="SEMESTRE2">Semestre 2</option>
                        <option value="ANNEE">Année complète</option>
                        <option value="HORS_PERIODE_SCOLAIRE">Hors periode scolaire</option>
                    </select>
                    </div>

                    <div class="col-6">
                    <label for="addDuree" class="form-label">durée (nombre de mois):</label>
                    <input type="number"  min="0" class="form-control" name="addDuree" id="addDuree">
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col">
                    <label for="addDepart" class="form-label">DEPART (JOUR/MOIS/ANNEE):</label>
                    <input type="date"   class="form-control" name="addDepart" id="addDepart">
                    </div>
                </div>
                
          </div>      
        
       
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnOrange" data-bs-dismiss="modal">Close</button>
                    <input id="Bouton" @click="$emit('ajouter')" type="submit" class="btnOrange" value="Ajouter" />
                 </div>
                 
            </form>
        
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
import { reactive } from "vue";

const listeEtudiants = reactive([])
const listeEtudiantsFiltres = reactive([])
const listePromo = reactive([])

const listeDestinations = reactive([])
const listeDestinationsFiltres = reactive([])

  function getEtud(){
    const fetchOptions = {method:"GET"};
    fetch("http://localhost:8989/api/etudiants/",fetchOptions)
    .then((response) => {return response.json()})
    .then((dataJSON) => {
      let listePromoDoublons=[]
      listeEtudiants.splice(0,listeEtudiants.length)
      dataJSON._embedded.etudiants.forEach((e)=>{
          listeEtudiants.push(e)
          listePromoDoublons.push(e.promo)
          })
      
      listePromoDoublons = [...new Set(listePromoDoublons)]  
      listePromo.push(listePromoDoublons)
      listeEtudiants.forEach((e)=>{
        
        if(e.promo==listePromo[0][0]){
          listeEtudiantsFiltres.push(e)
        }
      })
    
       
    })
    
    .catch((error) => console.log(error));
  }

  function getDestinations(){

      listeDestinations.splice(0,listeDestinations.length)         //On vide la liste des destinations avant de la remplir afin d'éviter les doublons
        listeDestinationsFiltres.splice(0,listeDestinationsFiltres.length) 

        let url = `http://localhost:8989/api/destinations`
        fetch(url)
        .then((res)=>{return res.json()})
        .then((json)=>{
            json._embedded.destinations.forEach((d)=>{
              listeDestinations.push(d)
              if(d.typeMobilite.toUpperCase()==document.getElementById("addTypeMobilite").value.toUpperCase()){
                listeDestinationsFiltres.push(d)
              }
            })})

  }


    getEtud()
    getDestinations()
  function setListeEtudiant(event){
    listeEtudiantsFiltres.splice(0,listeEtudiantsFiltres.length)
    listeEtudiants.forEach((e)=>{
      if(event.target.value==e.promo){
        listeEtudiantsFiltres.push(e)
      }
    })
   
  }

  function setListeDestinations(event){
    listeDestinationsFiltres.splice(0,listeDestinationsFiltres.length)
    listeDestinations.forEach((d)=>{
      if(event.target.value.toUpperCase()==d.typeMobilite.toUpperCase()){
        listeDestinationsFiltres.push(d)
      }
    })
    
   
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
#ajout{
    border-radius: 10px;
}
</style>