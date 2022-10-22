package com.example.demo.services;

import com.example.demo.entities.Article;
import com.example.demo.entities.FicheStock;
import com.example.demo.repositories.ArticleRepo;
import com.example.demo.repositories.FicheStockRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockServiceImp implements StockService{
    @Autowired
    private FicheStockRepository ficheStockRepository;
    @Autowired
    private ArticleRepo articleRepo;


    @Override
    public FicheStock trouverFiche(String designation) {
        return ficheStockRepository.findByDesignationEquals(designation);
    }

    @Override
    public FicheStock ajouterFiche(FicheStock ficheStock) {
        return ficheStockRepository.save(ficheStock);
    }

    @Override
    public List<FicheStock> recupererTous() {
        return ficheStockRepository.findAll();
    }

    @Override
    public FicheStock trouverParId(Long id) {
        return ficheStockRepository.getById(id);
    }

    @Override
    public Article ajouterArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public Article trouverParCodeBarre(String codeBarre) {
        return articleRepo.findByCodeBarreEquals(codeBarre);
    }

    @Override
    public Article trouverArticleParId(long id) {
        return articleRepo.getById(id);
    }

    @Override
    public List<FicheStock> recupererNotifications() {
        return ficheStockRepository.getNotification();
    }

    @Override
    public List<Article> recupererArticleSearch(String search) {
        return articleRepo.getArticleBySearch(search);
    }

    @Override
    public List<Article> recupererParAffectations(long id) {
        return articleRepo.findByDetailAffectation_IdEquals(id);
    }
}
