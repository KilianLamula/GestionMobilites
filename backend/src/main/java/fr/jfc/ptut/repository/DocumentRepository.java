package fr.jfc.ptut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.jfc.ptut.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    
}
