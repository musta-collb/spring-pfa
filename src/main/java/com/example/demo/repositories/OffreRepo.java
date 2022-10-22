package com.example.demo.repositories;

import com.example.demo.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepo extends JpaRepository<Offre, Long> {
    public Offre findByFournisseur_IdEqualsAndAppelOffre_IdEquals(Long id, Long idAppelOffre);
}
