package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "offre")
@Data
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dateCreation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;

    @OneToMany(mappedBy = "offre", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<DetailOffre> detailOffres = new ArrayList<>();

    public List<DetailOffre> getDetailOffres() {
        return detailOffres;
    }

    public void setDetailOffres(List<DetailOffre> detailOffres) {
        this.detailOffres = detailOffres;
    }

    public AppelOffre getAppelOffre() {
        return appelOffre;
    }

    public void setAppelOffre(AppelOffre appelOffre) {
        this.appelOffre = appelOffre;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}