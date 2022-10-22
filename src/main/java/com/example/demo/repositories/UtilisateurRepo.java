package com.example.demo.repositories;

import com.example.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {
    public Utilisateur findByDesignationEquals(String designation);
    @Query("SELECT u from Utilisateur u WHERE  ( u.description LIKE %:search% OR u.designation LIKE %:search% )")
    public List<Utilisateur> getBySearch(@Param("search") String search);
}
