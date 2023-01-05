package fr.jfc.ptut.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.entity.EtatMobilite;
import fr.jfc.ptut.entity.Etudiant;
import fr.jfc.ptut.entity.Mobilite;
import fr.jfc.ptut.entity.Type;
import fr.jfc.ptut.repository.EtudiantRepository;

@RestController
@RequestMapping(value="/api")
public class EtudiantController {

    @Autowired
    EtudiantRepository etudiantRepository;
    
    @GetMapping("/etudiant/promo")
    public Set<Integer> getPromo(){
        Set<Integer> res = new HashSet<>();
        //On charge tout les étudiants
        List<Etudiant> etudiants = etudiantRepository.findAll();
        //On parcourt les étudiants
        for (Etudiant etud: etudiants){
            res.add(etud.getPromo());
        }
        return res;
    }

    @GetMapping("/findEtudiant/promo/{promo}")
    public Set<Etudiant> getEtudiantByPromo(@PathVariable String promo){
        Integer promoEtud = Integer.valueOf(promo);
        Set<Etudiant> res = new HashSet<>();
        List<Etudiant> etudiants = etudiantRepository.findAll();
        for(Etudiant etud : etudiants){
            if(etud.getPromo().equals(promoEtud)){
                res.add(etud);
            }
        }
        return res;
    }

    @GetMapping("/findEtudiant/etatMobilite/{etat}")
    public Set<Etudiant> getEtudiantByEtatMobilite(@PathVariable String etat){
        Set<Etudiant> res = new HashSet<>();
        List<Etudiant> etudiants = etudiantRepository.findAll();
        //Requete pour étudiantayant validé mobilté
        if(etat.equals("val")){
            for(Etudiant etud : etudiants){
                if(!etud.getMobilites().isEmpty()){
                    res.add(etud);
                }
            }
        }
        //Requete pour étudiant n'ayant pas validé mobilité
        if(etat.equals("nVal")){
            for(Etudiant etud : etudiants){
                if(etud.getMobilites().isEmpty()){
                    res.add(etud);
                }
            }
        }
        return res;
    }

   
}
