package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "detail_affectation_id")
    private DetailAffectation detailAffectation;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "marche_id")
    private Marche marche;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "garantie_id")
    private Garantie garantie;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fiche_stock_id")
    private FicheStock ficheStock;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "lot_rebut_id")
    private LotRebut lotRebut;

    public DetailAffectation getDetailAffectation() {
        return detailAffectation;
    }

    public void setDetailAffectation(DetailAffectation detailAffectation) {
        this.detailAffectation = detailAffectation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String codeBarre;
    private String designation;
    private double prixUnitaire;
    private double prixTotale;
    private String description;
    private String conditionnement;
    private String marque;
    private double poids;
    private String image;
    private Date dateAcquisition;
    private String etat;

    @OneToMany(mappedBy = "article", orphanRemoval = true)
    private List<TicketReclamation> ticketReclamations = new ArrayList<>();

    public LotRebut getLotRebut() {
        return lotRebut;
    }

    public void setLotRebut(LotRebut lotRebut) {
        this.lotRebut = lotRebut;
    }

    public FicheStock getFicheStock() {
        return ficheStock;
    }

    public void setFicheStock(FicheStock ficheStock) {
        this.ficheStock = ficheStock;
    }

    public List<TicketReclamation> getTicketReclamations() {
        return ticketReclamations;
    }

    public void setTicketReclamations(List<TicketReclamation> ticketReclamations) {
        this.ticketReclamations = ticketReclamations;
    }

    public Garantie getGarantie() {
        return garantie;
    }

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}