package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acheteur")
@Data
@NoArgsConstructor
public class Acheteur {
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
    private String nom;
    private  String email;
    private String adresse;
    private String tel;

    @OneToMany(mappedBy = "acheteur", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<LotRebut> lotRebuts = new ArrayList<>();

    public List<LotRebut> getLotRebuts() {
        return lotRebuts;
    }

    public void setLotRebuts(List<LotRebut> lotRebuts) {
        this.lotRebuts = lotRebuts;
    }
}