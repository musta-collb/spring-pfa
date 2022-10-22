package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "marche")
@Data
@NoArgsConstructor
@JsonIgnoreProperties( {"offres","hibernateLazyInitializer", "handler", "articles", "fournisseur", "appelOffre"})
public class Marche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dateRealisation;
    private Date dateLivraison;
    private long quantite;
    private String objet;
    private String reference;

    @OneToMany(mappedBy = "marche", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Offre offre;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private AppelOffre appelOffre ;

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}