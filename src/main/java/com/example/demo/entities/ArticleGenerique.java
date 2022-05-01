package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "article_generique")
@Data
@NoArgsConstructor
public class ArticleGenerique {
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

    private String designation;
    private String unite;

    @OneToMany(mappedBy = "articleGenerique", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private Set<Quantite> quantites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "articleGenerique", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<DetailOffre> detailOffres = new ArrayList<>();

    public List<DetailOffre> getDetailOffres() {
        return detailOffres;
    }

    public void setDetailOffres(List<DetailOffre> detailOffres) {
        this.detailOffres = detailOffres;
    }

    public Set<Quantite> getQuantites() {
        return quantites;
    }

    public void setQuantites(Set<Quantite> quantites) {
        this.quantites = quantites;
    }
}