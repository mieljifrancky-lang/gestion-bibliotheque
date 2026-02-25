package com.mielji.bibliotheque.repository;

import com.mielji.bibliotheque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    // On pourra filtrer par utilisateur ou document plus tard
}