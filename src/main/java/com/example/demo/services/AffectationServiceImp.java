package com.example.demo.services;

import com.example.demo.entities.DetailAffectation;
import com.example.demo.entities.Utilisateur;
import com.example.demo.repositories.DetailAffectationRepo;
import com.example.demo.repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffectationServiceImp implements AffectationService{
    @Autowired
    private UtilisateurRepo utilisateurRepo;
    @Autowired
    private DetailAffectationRepo detailAffectationRepo;
    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.save(utilisateur);
    }

    @Override
    public Utilisateur recupererParDesignation(String designation) {
        return utilisateurRepo.findByDesignationEquals(designation);
    }

    @Override
    public List<Utilisateur> recupererTousUtilisateurs() {
        return utilisateurRepo.findAll();
    }

    @Override
    public Utilisateur recupererUtilisateurParId(long id) {
        return utilisateurRepo.getById(id);
    }

    @Override
    public List<Utilisateur> recupererParSearch(String search) {
        return utilisateurRepo.getBySearch(search);
    }

    @Override
    public DetailAffectation ajouterDetailAffectation(DetailAffectation detailAffectation) {
        return detailAffectationRepo.save(detailAffectation);
    }

    @Override
    public List<DetailAffectation> recupererTousAffectations() {
        return detailAffectationRepo.findAll();
    }

    @Override
    public void supprimerAffectation(long id) {
        detailAffectationRepo.deleteById(id);
    }

    @Override
    public DetailAffectation recupererAffectationParId(long id) {
        return detailAffectationRepo.getById(id);
    }
}
