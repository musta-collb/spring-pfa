package com.example.demo.api;


import com.example.demo.entities.Article;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.FicheStock;
import com.example.demo.services.FournisseurService;
import com.example.demo.services.StockService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private StockService stockService;
    @Autowired
    private FournisseurService fournisseurService;
    @PostMapping("")
    public ResponseEntity<String> ajouterArticle(@RequestBody ArticleDetails articleDetails){
        Article article = articleDetails.getArticle();
        Article test = stockService.trouverParCodeBarre(articleDetails.getArticle().getCodeBarre());
        if(test !=null){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Article de meme code existe déjà");
        }
        FicheStock ficheStock = stockService.trouverParId(articleDetails.getCategorie());
        ficheStock.setQuantiteDisponible(ficheStock.getQuantiteDisponible()+ 1);
        stockService.ajouterFiche(ficheStock);
        article.setFicheStock(stockService.trouverParId(articleDetails.getCategorie()));
        article.setMarche(fournisseurService.recupererMarcheParId(articleDetails.getMarcheId()));
        stockService.ajouterArticle(article);
        return ResponseEntity.ok().body("Article \""+articleDetails.getArticle().getCodeBarre()+"\" est bien crée");
    }

    @Data
    @NoArgsConstructor
    static class ArticleDetails{
        private Article article;
        private long categorie;
        private long marcheId;
    }


    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable long id){
        Article article = new Article();
        try {
            article = stockService.trouverArticleParId(id);
        }
        catch (HttpMessageNotReadableException e){

        }finally {
            return article;
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable long id, @RequestBody Article article){
        System.out.println(article);
        Article test =stockService.trouverParCodeBarre(article.getCodeBarre());
        if(test.getId() != article.getId() ){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("il existe déjà un article avec le meme code barre");
        }
        if(test == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas d'article avec cet Id");
        }
        Article articleT = stockService.trouverArticleParId(id);
        articleT.setMarche(fournisseurService.recupererMarcheParId(article.getMarche().getId()));
        articleT.setFicheStock(stockService.trouverParId(article.getFicheStock().getId()));
        articleT.setDesignation(article.getDesignation());
        articleT.setConditionnement(article.getConditionnement());
        articleT.setCodeBarre(article.getCodeBarre());
        articleT.setDateAcquisition(article.getDateAcquisition());
        articleT.setDescription(article.getDescription());
        articleT.setEtat(article.getEtat());
        articleT.setImage(article.getImage());
        articleT.setMarque(article.getMarque());
        articleT.setPoids(article.getPoids());
        articleT.setPrixTotale(article.getPrixTotale());
        articleT.setPrixUnitaire(article.getPrixUnitaire());
        stockService.ajouterArticle(articleT);
        return  ResponseEntity.ok().body("Article modifié avec succéss");
    }

    @GetMapping("/search/{search}")
    public  List<Article> getArticlesBySearch(@PathVariable String search){
        System.out.println(search);
        try {
            List<Article>  articles= stockService.recupererArticleSearch(search);
            articles.forEach(article -> {
                article.setFicheStock(null);
                article.setMarche(null);
            });
            return articles;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    };
}
