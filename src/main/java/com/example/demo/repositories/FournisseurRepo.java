package com.example.demo.repositories;

import com.example.demo.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepo extends JpaRepository<Fournisseur, Long> {
}
