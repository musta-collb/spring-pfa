package com.example.demo.repositories;

import com.example.demo.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findByEmailEquals(@NonNull String email);

    Personnel findByEmailEqualsAndPasswordEquals(String email, String password);

}