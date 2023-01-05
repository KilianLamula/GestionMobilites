package fr.jfc.ptut.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Mobilite;

public interface MobiliteRepository extends JpaRepository<Mobilite, Integer> {
    
}
