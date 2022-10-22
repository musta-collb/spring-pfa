package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "detail_offre")
@Data
@NoArgsConstructor
public class DetailOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "offre_id")
    private Offre offre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "article_generique_id")
    private ArticleGenerique articleGenerique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private double prixUnitaire;
    private String marque;
    private double quantite;
    private String detail;
    private Date dateCreation;

    public ArticleGenerique getArticleGenerique() {
        return articleGenerique;
    }

    public void setArticleGenerique(ArticleGenerique articleGenerique) {
        this.articleGenerique = articleGenerique;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }
}