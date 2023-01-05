package fr.jfc.ptut.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private Integer numEtud;
    
    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private Integer promo;

    @JsonIgnore
    @OneToMany(mappedBy="etudiant")
    @ToString.Exclude
    private List<Mobilite> mobilites = new ArrayList<>();

}