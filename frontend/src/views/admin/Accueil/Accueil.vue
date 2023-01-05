<template>

      <div id="accueil" class="overflow-auto mb-0 d-flex flex-column justify-content-around  ">
            <div class="col-12 col-md-6 m-auto hauteur">
                  <Carousel/>
            </div>
            <div class="col-12 col-md-6  m-auto">
                  <div class=" row largeur  pb-5 pt-0 pb-md-5  justify-content-around g-3 gy-xl-0 gx-md-1">    

                        <Carte v-bind:nom="'ETUDIANTS'" v-bind:lien="'/admin/etudiants'" v-bind:chemin="etudiants"/>
                        <Carte v-bind:nom="'DESTINATIONS'" v-bind:lien="'/admin/destinations'" v-bind:chemin="destinations"/>
                        <Carte v-bind:nom="'MOBILITES'" v-bind:lien="'/admin/mobilites'" v-bind:chemin="mobilites"/>
                        <Carte v-bind:nom="nomDoc" v-bind:lien="'/admin/documents'" v-bind:chemin="doc"/>
                  </div>
            </div>
      </div>
    
</template>

<style>

#accueil{
      height: 85%;
      overflow: auto;
}
.hauteur{
 height: 50vh;
}

@media all and (orientation: portrait) {
      .hauteur{
            height: 28vh;
      }  
}

</style>

<script setup>
import "bootstrap/dist/js/bootstrap.min.js";

import Carte from "../../../components/CarteAccueil.vue"
import Carousel from "../../../components/Carousel.vue"

import etudiants from "./img/etudiants.png" 
import destinations from "./img/img_destinations.png" 
import mobilites from "./img/img_mobilités.png" 
import doc from "./img/img_docsadmin.png"

//navabr active
    let listeNav = ["accueilNav","etudiantNav","destinationsNav","mobilitesNav","docNav","siteNav"]
    for(let l of listeNav){
    document.getElementById(l).classList.remove("active")
    }
    document.getElementById("accueilNav").classList.add("active")


let nomDoc= "DOCUMENTS ADMIN."
if(window.innerWidth < window.innerHeight  ){
      nomDoc="DOCUMENTS"
}
</script>

