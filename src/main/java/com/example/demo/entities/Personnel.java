package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personnel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "utilisateur","password","ticketReclamations"})
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnoreProperties
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    private  String nom;
    private  String prenom;
    private  String email;
    private  String password;
    private  String fonction;
    private  String image;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "personnel_roles",
            joinColumns = @JoinColumn(name = "personnel_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "personnel", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TicketReclamation> ticketReclamations = new ArrayList<>();
}