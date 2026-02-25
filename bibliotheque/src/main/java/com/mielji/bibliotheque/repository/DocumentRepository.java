package com.mielji.bibliotheque.repository;

import com.mielji.bibliotheque.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // On pourra ajouter des méthodes spécifiques si besoin
}