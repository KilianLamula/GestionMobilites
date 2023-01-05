import { createRouter, createWebHistory } from "vue-router";

//Admin :
import Accueil from "@/views/admin/accueil/Accueil.vue";
import Etudiants from "@/views/admin/etudiants/Etudiants.vue";
import Destinations from "@/views/admin/destinations/Destinations.vue";
import Mobilites from "@/views/admin/mobilites/Mobilites.vue";
import DocsAdmin from "@/views/admin/docsAdmin/DocsAdmin.vue";

//Etudiant :
import AccueilEtud from "@/views/etudiant/accueil/Accueil.vue";
import DestinationsEtud from "@/views/etudiant/destinations/Destinations.vue";
import DocsAdminEtud from "@/views/etudiant/docsAdmin/DocsAdmin.vue";

const routes = [
  {
    path: "/admin",
    name: "Accueil",
    component: Accueil,
  },

  { path: "/admin/etudiants",
    name: "Etudiants",
    component: Etudiants,
  },

  { path: "/admin/destinations",
    name: "Destinations",
    component: Destinations,
  },

  { path: "/admin/mobilites",
    name: "Mobilites",
    component: Mobilites,
  },

  { path: "/admin/documents",
    name: "DocsAdmin",
    component: DocsAdmin,
  },

  { path: "/etudiant",
    name: "AccueilEtud",
    component: AccueilEtud,
  },

  { path: "/etudiant/destinations",
    name: "DestinationsEtud",
    component: DestinationsEtud,
  },

  { path: "/etudiant/documents",
    name: "DocsAdminEtud",
    component: DocsAdminEtud,
  },

];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
