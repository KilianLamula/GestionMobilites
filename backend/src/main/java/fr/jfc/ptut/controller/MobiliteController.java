package fr.jfc.ptut.controller;

import java.util.Set;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.Destination;
import fr.jfc.ptut.entity.EtatMobilite;
import fr.jfc.ptut.entity.Etudiant;
import fr.jfc.ptut.entity.Mobilite;
import fr.jfc.ptut.entity.Type;
import fr.jfc.ptut.repository.MobiliteRepository;

@RestController
@RequestMapping(path = "/api")
public class MobiliteController {
    
    @Autowired
    private MobiliteRepository mobiliteRepository;

    @GetMapping(value="/mobilites/promotions")
    /**
     * 
     * @return une liste contenant toutes les promotions des étudiants ayant effectué une mobilité
     */
    public Set<Integer> findAllPromo(){

        //On initialise la liste de promotions qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<Integer> promotions = new HashSet<>();

        //On recupère toutes les destinations
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on récupère les étudiants dans une liste
        for (Mobilite m : mobilites){
            promotions.add(m.getEtudiant().getPromo());
        }

        return promotions;
    }

    @GetMapping(value="/mobilites/destinations")
    /**
     * 
     * @return une liste contenant toutes les destinations des mobilités effectuées
     */
    public Set<Destination> findAllDestinations(){

        //On initialise la liste des destinations qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<Destination> destinations = new HashSet<>();

        //On récupère toutes les destinations
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on récupère la destination (nom de lé'établissement + ville) dans la Set
        for (Mobilite m : mobilites){
            destinations.add(m.getDestination());
        }
        return destinations;
    }

    
    @GetMapping(value="/mobilites/etatsMobilite")
    /**
     * @return une liste contenant tous les états de mobilité des mobilités existantes
     */
    
    public Set<String> findAllEtatsMobilite(){

        //On initialise la liste des statuts de contrat qu'on renvoie, c'est une Set car on ne veut pas de doublons
        Set<String> etatsMobilite = new HashSet<>();

        //On initialise une liste qui va contenir tous les états de mobilité
        List<EtatMobilite> etats = new ArrayList<>();

        //On recupère toutes les mobilités
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on récupère l'état de la mobilité via la méthode etatMobilite()
        for (Mobilite m : mobilites){
            etats.add(m.etatMobilite());
        }

        //on parcourt chaque état de mobilité et en fonction de sa valeur, on remplit la Set de String prédéfinis
        for (EtatMobilite e : etats){
            if (e == EtatMobilite.NON_VALIDEE){
                etatsMobilite.add("Non Validée");
            } else if (e == EtatMobilite.EN_COURS){
                etatsMobilite.add("En Cours");
            } else {
                etatsMobilite.add("Validée");
            }
        }
        
        return etatsMobilite;
    }

    @GetMapping(value="/mobilites/findByPromo")
    /**
     * 
     * @param promo la promotion sélectionnée, servant à filtrer les mobilités
     * @return une liste de toutes les mobilités associées à une promotion
     */
    public List<Mobilite> findByPromo(Integer promo){

        //On initialise la liste des mobilités qu'on renvoie
        List<Mobilite> listeMobilitesByPromo = new ArrayList<>();

        //On recupère toutes les moblités
        List<Mobilite> allMobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on l'ajoute à listeMobilitesByPromo 
        //si la promotion de l'étudiant associé à la mobilité correspond à la promotion sélectionnée
        for (Mobilite mobilite : allMobilites){
            if (mobilite.getEtudiant().getPromo() == promo.intValue()){
                listeMobilitesByPromo.add(mobilite);
            }
        }
        return listeMobilitesByPromo;
    }

    @GetMapping(value="/mobilites/findByIdDestination")
    /**
     * 
     * @param destination la destination sélectionnée, servant à filtrer les mobilités
     * @return une liste de toutes les mobilités associées à une destination
     */
    
    public List<Mobilite> findByIdDestination(Destination destination){

        //On initialise la liste des mobilités qu'on renvoie
        List<Mobilite> listeMobilitesByDestination = new ArrayList<>();

        //On recupère toutes les moblités
        List<Mobilite> allMobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on l'ajoute à listeMobilitesByDestination 
        //si la destination associée à la mobilité correspond à la destination sélectionnée
        for (Mobilite mobilite : allMobilites){
            if (mobilite.getDestination().getId() == destination.getId()){
                listeMobilitesByDestination.add(mobilite);
            }
        }
        return listeMobilitesByDestination;
    }
    
    @GetMapping(value="/mobilites/findByEtatMobilite")
    /**
     * 
     * @param statut le statut sélectionné, servant à filtrer les mobilités
     * @return une liste de toutes les mobilités associées à un état de mobilité
     */
    public List<Mobilite> findByEtatMobilite(String etat){

        //On crée une String qui va stocker l'état de chaque mobilité et que l'on va comparer à la String passée en paramètre
        String etatMobilite;

        //On initialise la liste des mobilités qu'on renvoie
        List<Mobilite> listeMobilitesByEtat = new ArrayList<>();

        //On recupère toutes les mobilités
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on l'ajoute à listeMobilitesByEtat 
        //si l'état de la mobilité correpond à l'état sélectionné
        for (Mobilite mobilite : mobilites){
            if (mobilite.etatMobilite() == EtatMobilite.NON_VALIDEE){
                etatMobilite = "Non Validée"; //on attribue la valeur "Non Validée" à etatMobilite pour la comparer à l'état passé en paramètre (de type String)
            } else if (mobilite.etatMobilite() == EtatMobilite.EN_COURS){
                etatMobilite = "En Cours";
            } else {
                etatMobilite = "Validée";
            }

            //on compare l'état de chaque mobilité à l'état passé en paramètre
            if (etatMobilite.equals(etat)){
                listeMobilitesByEtat.add(mobilite);
            }
        }

        return listeMobilitesByEtat;
    }

    @GetMapping(value="/mobilites/findByEtudiant")
    /**
     * 
     * @param etudiant le nom ou prénom d'un étudiant saisi par l'utilisateur, servant à filtrer les mobilités
     * @return une liste de toutes les mobilités associées à un étudiant
     */
    public List<Mobilite> findByEtudiant(String etudiant){

        //On convertit la saisie en minuscule
        String saisie = etudiant.toLowerCase();
        //On initialise la liste des mobilités qu'on renvoie
        List<Mobilite> listeMobilitesByEtudiant = new ArrayList<>();

        //On recupère toutes les mobilités
        List<Mobilite> mobilites = mobiliteRepository.findAll();

        //on parcourt chaque mobilité et on l'ajoute à listeMobilitesByEtat 
        //si le prénom ou nom de l'étudiant correpond à la saisie utilisateur
        
        for (Mobilite mobilite : mobilites){
            if (mobilite.getEtudiant().getNom().toLowerCase().contains(saisie) || 
            mobilite.getEtudiant().getPrenom().toLowerCase().contains(saisie)){
                listeMobilitesByEtudiant.add(mobilite);
            }
        }
        return listeMobilitesByEtudiant;
    }
    
}
