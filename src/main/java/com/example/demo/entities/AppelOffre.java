package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "appel_offre")
@Data
@NoArgsConstructor

@JsonIgnoreProperties( {"offres","hibernateLazyInitializer", "handler"})
public class AppelOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Date dateCreation;
    private double budget;
    private String description;
    private Date dateLimite;
    private String objet;
    private String reference;
    public AppelOffre(long id, String description){
        this.id = id;
        this.description = description;
    }
    @OneToMany(mappedBy = "appelOffre", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Offre> offres = new ArrayList<>();

    @OneToMany(mappedBy = "appelOffre", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true,
    fetch = FetchType.LAZY)
    private List<Quantite> quantites = new ArrayList<>();

    public List<Quantite> getQuantites() {
        return quantites;
    }

    public void setQuantites(List<Quantite> quantites) {
        this.quantites = quantites;
    }

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }
}