<template>
<div id="accueil" class="overflow-auto mb-0 d-flex flex-column justify-content-center ">
    <div class="col-12 col-md-6 m-auto">
        <Carousel/>
    </div>
    <div class="col-12">
        <div class="row d-flex justify-content-center pb-5 pt-0 pb-md-5 gy-xl-0 gx-md-1">    
            <Carte v-bind:nom="'DESTINATIONS'" v-bind:lien="'/etudiant/destinations'" v-bind:chemin="destinations"/>
            <Carte v-bind:nom="nomDoc" v-bind:lien="'/etudiant/documents'" v-bind:chemin="doc"/>
        </div>
        
    </div>
</div>
</template>

<script setup>
import Carte from "../../../components/CarteAccueilEtud.vue"
import Carousel from "../../../components/Carousel.vue"

import destinations from "../../admin/accueil/img/img_destinations.png" 
import doc from "../../admin/accueil/img/img_docsadmin.png"

let nomDoc= "DOCUMENTS ADMIN."
if(window.innerWidth < window.innerHeight  ){
    nomDoc="DOCUMENTS"
}
</script>

<style>
</style>