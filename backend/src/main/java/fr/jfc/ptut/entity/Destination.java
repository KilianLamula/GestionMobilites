package fr.jfc.ptut.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class Destination {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private Type typeMobilite;

    private int nbPlaceSemestre;

    private int nbPlaceAnnee;

    private LocalDate dateFinDeContratIsis;

    @NonNull
    private String nomEtablissementAccueil;

    @Column(columnDefinition="LONGTEXT")
    private String image;

    @NonNull
    private String ville;

    @NonNull
    private String pays;

    @JsonIgnore
    @OneToMany(mappedBy = "destination")
    @ToString.Exclude
    private List<Mobilite> mobilites = new ArrayList<>();

}
