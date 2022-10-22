package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fournisseur")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "offres", "marches"})
public class Fournisseur {
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
    //attributes
    private String raisonSociale;
    private String adresse;
    private String mail;
    private String tel;
    private String pays;
    private String secteur;
    private int nbAnnees;
    private String codePostale;

    @OneToMany(mappedBy = "fournisseur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Marche> marches = new ArrayList<>();

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offre> offres = new ArrayList<>();

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }

    public List<Marche> getMarches() {
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }
}