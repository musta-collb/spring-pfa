package com.example.demo.repositories;

import com.example.demo.entities.FicheStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FicheStockRepository extends JpaRepository<FicheStock, Long> {
    FicheStock findByDesignationEquals(String designation);
    @Query("SELECT e from FicheStock e where e.quantiteDisponible < e.quantiteMin or e.quantiteMax < e.quantiteDisponible")
    List<FicheStock> getNotification();

}
