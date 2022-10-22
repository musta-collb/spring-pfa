package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "quantite")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "appelOffre"})
public class Quantite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH} , fetch = FetchType.LAZY)
    @JoinColumn(name = "article_generique_id")
    private ArticleGenerique articleGenerique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private double quantite;

    public ArticleGenerique getArticleGenerique() {
        return articleGenerique;
    }

    public void setArticleGenerique(ArticleGenerique articleGenerique) {
        this.articleGenerique = articleGenerique;
    }

    public AppelOffre getAppelOffre() {
        return appelOffre;
    }

    public void setAppelOffre(AppelOffre appelOffre) {
        this.appelOffre = appelOffre;
    }
}