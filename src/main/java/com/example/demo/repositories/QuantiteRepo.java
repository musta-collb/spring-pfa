package com.example.demo.repositories;

import com.example.demo.entities.Quantite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantiteRepo extends JpaRepository<Quantite, Long> {
    Quantite findByArticleGenerique_IdEqualsAndAppelOffre_IdEquals(Long id, Long id1);
}
