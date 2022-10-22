package com.example.demo.services;

import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Marche;

import java.util.List;

public interface FournisseurService {
    public Fournisseur ajouterFournisseur(Fournisseur fournisseur);
    public Fournisseur recupererParId(long id);
    public List<Fournisseur> recupererTous();
    public Marche ajouterMarche(Marche marche);
    public Marche recupererMarcheParId(long id);
    public List<Marche> recupererTousMarche();
}

