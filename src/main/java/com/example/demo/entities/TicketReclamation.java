package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket_recl" +
        "amation")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketReclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private Date date;
    private String motif;
    private String type;
    private String etat;
    @OneToMany(mappedBy = "ticketReclamation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Commantaire> commantaires = new ArrayList<>();

    @OneToMany(mappedBy = "ticketReclamation", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Intervention> interventions = new ArrayList<>();


}