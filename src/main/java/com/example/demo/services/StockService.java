package com.example.demo.services;

import com.example.demo.entities.Article;
import com.example.demo.entities.FicheStock;

import java.util.List;

public interface StockService {
    public FicheStock trouverFiche(String designation);
    public FicheStock ajouterFiche(FicheStock ficheStock);
    public List<FicheStock> recupererTous();
    public FicheStock trouverParId(Long id);

    public Article ajouterArticle(Article article);
    public Article trouverParCodeBarre(String codeBarre);
    public Article trouverArticleParId(long id);
    public List<FicheStock> recupererNotifications();
    public List<Article> recupererArticleSearch(String search);
    public List<Article> recupererParAffectations(long id);
}
