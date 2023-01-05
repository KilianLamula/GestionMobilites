package fr.jfc.ptut.entity;
import java.time.LocalDate;
import javax.persistence.*;


import org.springframework.context.annotation.Lazy;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Lazy
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Mobilite {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @NonNull
    private LocalDate dateDepart;
    
    private String retourExperience;

    private int dureeEnMois;

    @NonNull
    private Periode periode;
    
    @NonNull
    @JsonIgnore
    @ManyToOne(optional = false) //obligatoire car clé étrangère non nulle
    private Etudiant etudiant;

    @NonNull
    @ManyToOne(optional = false)
    private Destination destination;

    public EtatMobilite etatMobilite(){        
        EtatMobilite res = EtatMobilite.NON_VALIDEE;
        //date de fin
        LocalDate dateFin = dateDepart.plusMonths(dureeEnMois);
        //Si mobilité en cours
        if(LocalDate.now().isAfter(dateDepart) && LocalDate.now().isBefore(dateFin)){
            //on renvoie etat en cours
            res = EtatMobilite.EN_COURS;
        }
        //Si mobilité fini
        else if(LocalDate.now().isAfter(dateFin)){
            //on renvoie etat validé
            res = EtatMobilite.VALIDEE;
        }
        return res;
    }
    
}
