package com.example.demo.services;

import com.example.demo.entities.AppelOffre;
import com.example.demo.entities.ArticleGenerique;
import com.example.demo.entities.Quantite;
import com.example.demo.repositories.AppelOffreRepo;
import com.example.demo.repositories.ArticleGeneriqueRepo;
import com.example.demo.repositories.QuantiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppelOffreServiceImp implements AppelOffreService {
    @Autowired
    private AppelOffreRepo appelOffreRepo;
    @Autowired
    private QuantiteRepo quantiteRepo;
    @Autowired
    private ArticleGeneriqueRepo articleGeneriqueRepo;

    @Override
    public AppelOffre ajouterAppelOffre(AppelOffre appelOffre) {
        return appelOffreRepo.save(appelOffre);
    }

    @Override
    public ArticleGenerique ajouterArcticle(ArticleGenerique articleGenerique) {
        return articleGeneriqueRepo.save(articleGenerique);
    }

    @Override
    public Quantite ajouterQuantite(Quantite quantite) {
        return quantiteRepo.save(quantite);
    }

    @Override
    public AppelOffre reccupererParId(long id) {
        return appelOffreRepo.getById(id);
    }

    @Override
    public List<AppelOffre> recupererTous() {
        return appelOffreRepo.findAll();
    }

    @Override
    public ArticleGenerique getArticleGeneriqueParId(long id) {
        return articleGeneriqueRepo.getById(id);
    }

    @Override
    public Quantite trouverParAppelOffreArticleGenr(long id, long id1) {
        return quantiteRepo.findByArticleGenerique_IdEqualsAndAppelOffre_IdEquals(id, id1);
    }

    @Override
    public AppelOffre recupererParReference(String reference) {
        return appelOffreRepo.findByReferenceEquals(reference);
    }

    @Override
    public void supprimerAppelOffre(long id) {
        appelOffreRepo.deleteById(id);
    }
}
