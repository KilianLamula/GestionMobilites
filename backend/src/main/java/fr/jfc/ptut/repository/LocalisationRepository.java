package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Localisation;

public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {
    
}
