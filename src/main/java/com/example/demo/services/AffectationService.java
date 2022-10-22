package com.example.demo.services;

import com.example.demo.entities.DetailAffectation;
import com.example.demo.entities.Utilisateur;

import java.util.List;

public interface AffectationService {
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
    public Utilisateur recupererParDesignation(String designation);
    public List<Utilisateur> recupererTousUtilisateurs();
    public Utilisateur recupererUtilisateurParId(long id);
    public List<Utilisateur> recupererParSearch(String search);
    public DetailAffectation ajouterDetailAffectation(DetailAffectation detailAffectation);
    public List<DetailAffectation> recupererTousAffectations();
    public void supprimerAffectation(long id);
    public DetailAffectation recupererAffectationParId(long id);
}
