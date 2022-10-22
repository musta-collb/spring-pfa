package com.example.demo.services;

import com.example.demo.entities.AppelOffre;
import com.example.demo.entities.ArticleGenerique;
import com.example.demo.entities.Quantite;
import org.springframework.data.jpa.repository.query.Jpa21Utils;

import java.util.List;

public interface AppelOffreService {
    public AppelOffre ajouterAppelOffre(AppelOffre appelOffre);
    public ArticleGenerique ajouterArcticle(ArticleGenerique articleGenerique);
    public Quantite ajouterQuantite(Quantite quantite);

    public AppelOffre reccupererParId(long id);

    //list of appels de offres

    public List<AppelOffre> recupererTous();

    public ArticleGenerique getArticleGeneriqueParId(long id);
    public Quantite trouverParAppelOffreArticleGenr(long id, long id1);
    public AppelOffre recupererParReference(String reference);
    public void supprimerAppelOffre(long id);
}
