package com.example.demo.repositories;

import com.example.demo.entities.Marche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcheRepo extends JpaRepository<Marche, Long> {
}
