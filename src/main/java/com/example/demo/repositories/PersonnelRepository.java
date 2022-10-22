package com.example.demo.repositories;

import com.example.demo.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findByEmailEquals(@NonNull String email);

    Personnel findByEmailEqualsAndPasswordEquals(String email, String password);

    @Query("SELECT p from Personnel p WHERE  ( p.nom LIKE %:search% OR p.prenom LIKE %:search%  OR p.email LIKE %:search% OR p.fonction LIKE %:search% )")
    public List<Personnel> findBySearch(@Param("search") String search);

}