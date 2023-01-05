<template>

  <div class="container-fluid overflow-scroll h-100">
    <HeaderAdmin/>
    <router-view />
    <Footer class="footer" />
  </div>
</template>

<script setup>
  import HeaderAdmin from './components/HeaderAdmin.vue';
  import Accueil from './views/admin/accueil/Accueil.vue';
  import Footer from './components/Footer.vue';
    
</script>


<style>
@font-face
{
    font-family: 'Bebas Neue';
    src: url('../public/polices/BebasNeue-Regular.eot');
    src: url('../public/polices/BebasNeue-Regular.woff') format('woff'),
         url('../public/polices/BebasNeue-Regular.ttf') format('truetype');
    font-weight: normal;
    font-style: normal;
}

html {
 
  margin: 0;
  height: 100%;
  width: 100%;

}

#app {
    background: #f3f3f3;
    font-family: 'Montserrat', sans-serif;
    margin: auto;
    padding: 0;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    
  }

.footer{
    position: absolute;
    bottom: 0;
    width: 100%;
   
  }

  h1{
    font-family: 'Bebas Neue', sans-serif;
  }

  .btnOrange{
    background-color: #B74803;
    border-radius: 30px;
    font-family: 'Bebas Neue';
    text-transform: uppercase;
    color: white;
    font-size: 24px;
    border: 0px;
    padding: 10px;
}

.inputFiltre{
  background-color: #A3B4C859;
  border-radius: 10px;
  border: 0px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.filtreTab{
  background-color: #A3B4C859;
  border-radius: 10px;
  border: 0px;
}

.scrollTableau{
    height: 32em;
    overflow-y: scroll ;
  }

</style>


