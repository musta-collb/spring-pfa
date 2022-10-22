package com.example.demo.services;

import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Marche;
import com.example.demo.repositories.FournisseurRepo;
import com.example.demo.repositories.MarcheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FournisseurServiceImp implements FournisseurService{
    @Autowired
    private FournisseurRepo fournisseurRepo;

    @Autowired
    private MarcheRepo marcheRepo;

    @Override
    public Fournisseur ajouterFournisseur(Fournisseur fournisseur) {
        return fournisseurRepo.save(fournisseur);
    }

    @Override
    public Fournisseur recupererParId(long id) {
        return fournisseurRepo.getById(id);
    }

    @Override
    public List<Fournisseur> recupererTous() {
        return fournisseurRepo.findAll();
    }

    @Override
    public Marche ajouterMarche(Marche marche) {
        return marcheRepo.save(marche);
    }

    @Override
    public Marche recupererMarcheParId(long id) {
        return marcheRepo.getById(id);
    }

    @Override
    public List<Marche> recupererTousMarche() {
        return marcheRepo.findAll();
    }
}
