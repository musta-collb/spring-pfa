package com.example.demo.repositories;

import com.example.demo.entities.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppelOffreRepo extends
        JpaRepository<AppelOffre, Long> {
    AppelOffre findByReferenceEquals(String reference);

}
