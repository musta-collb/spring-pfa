package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "personnels","detailAffectations"})
public class Utilisateur {
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

    private String utilisateur;
    private  String description;
    private String designation;

    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Personnel> personnels = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<DetailAffectation> detailAffectations = new ArrayList<>();

}