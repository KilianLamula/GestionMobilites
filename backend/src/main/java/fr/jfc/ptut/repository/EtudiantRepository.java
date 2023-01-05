package fr.jfc.ptut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Etudiant;
import fr.jfc.ptut.entity.Mobilite;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    
    /**
     * @param nom entré par l'utilisateur
     * @param prenom entré par l'utilisateur
     * @return la liste des étudiants dont le nom et/ou le prénom correspondent 
     * à la chaine de caractères entrée par l'utilisateur
     */
    List<Etudiant> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

}
