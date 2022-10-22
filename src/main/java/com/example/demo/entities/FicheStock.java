package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fiche_stock")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","articles"})
public class FicheStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String designation;
    private double quantiteMin;
    private double quantiteMax;
    private double quantiteDisponible;
    private String unite;
    @OneToMany(mappedBy = "ficheStock", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

}