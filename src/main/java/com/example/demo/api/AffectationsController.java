package com.example.demo.api;

import com.example.demo.entities.Article;
import com.example.demo.entities.DetailAffectation;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Utilisateur;
import com.example.demo.services.AffectationService;
import com.example.demo.services.PersonnelService;
import com.example.demo.services.StockService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/affectations")
public class AffectationsController {
    @Autowired
    private StockService stockService;
    @Autowired
    private AffectationService affectationService;
    @Autowired
    private PersonnelService personnelService;
    @GetMapping("")
    public List<DetailAffectation> getAffectations(){
        return  affectationService.recupererTousAffectations();
    }

    @PostMapping("/utilisateur")
    public String ajouterUtilisateur(@RequestBody DetailUtilisateur utilisateur){
        System.out.println(" "+ utilisateur.getDescription()+" "+ utilisateur.getDescription());
        if(affectationService.recupererParDesignation(utilisateur.getDesignation()) != null){
            return "Un expoiteur de meme nom existe déjà!";
        }
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setDescription(utilisateur.getDescription());
        utilisateur1.setDesignation(utilisateur.getDesignation());
        affectationService.ajouterUtilisateur(utilisateur1);
        utilisateur.getPersonnels().forEach((personnel -> {
            Personnel personnel1 = personnelService.trouverParId(personnel.getId());
            personnel1.setUtilisateur(utilisateur1);
            personnelService.ajouterPersonnel(personnel1);
        }));
        affectationService.ajouterUtilisateur(utilisateur1);
      return "Votre Exploiteur a été bien crée";
    };
    @Data
    @NoArgsConstructor
    static class DetailUtilisateur{
        private String description;
        private String designation;
        private List<Personnel> personnels;
    }

    @GetMapping("/exploiteurs")
    public List<Utilisateur> getExploiteurs(){
        return affectationService.recupererTousUtilisateurs();
    }
    @GetMapping("/exploiteurs/{id}/personnels")
    public List<Personnel> getPersonnelParExploiteur(@PathVariable long id) {
        try {
            List<Personnel> personnels = affectationService.recupererUtilisateurParId(id).getPersonnels();
            personnels.forEach((personnel -> {
                personnel.setRoles(null);
            }));
            return personnels;
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    @DeleteMapping("/exploiteurs/personnels/{id}")
    public String supprimerPersonnelExploiteur(@PathVariable long id){
        try {
            Personnel personnel = personnelService.trouverParId(id);
            personnel.setUtilisateur(null);
            personnelService.ajouterPersonnel(personnel);
            return "Votre personnel a été supprimer";
        }catch (Exception e){
            return  "Aucun personnel n'a été trouvé";
        }
    }

    @GetMapping("/exploiteurs/search/{search}")
    public List<Utilisateur> getBySearch(@PathVariable String search){

        return affectationService.recupererParSearch(search);

    }
    @PostMapping("")
    public String addExploiteur(@RequestBody Exploiteur exploiteur){
        System.out.println("Called add Exploiteur!");
        try {
            Utilisateur ex = affectationService.recupererUtilisateurParId(exploiteur.getExploiteur().getId());
            DetailAffectation detailAffectation = exploiteur.getDetailAffectation();
            detailAffectation.setUtilisateur(ex);
            affectationService.ajouterDetailAffectation(detailAffectation);
            //
            exploiteur.getArticles().forEach(article -> {
                Article a = stockService.trouverArticleParId(article.getId());
                a.setDetailAffectation(detailAffectation);
                stockService.ajouterArticle(a);
            });
            return "affectation bien ajouté";
        }catch (Exception e){
            return "Article ou exploiteur non trouvé";
        }
    }
    @Data
    @NoArgsConstructor
    static class Exploiteur{
        private Utilisateur exploiteur;
        private List<Article> articles;
        private DetailAffectation detailAffectation;
    }


    @DeleteMapping("/{id}")
    public String supprimerAffectation(@PathVariable long id){
        try{
            List<Article> articles = affectationService.recupererAffectationParId(id).getArticles();
            articles.forEach(article->{
                article.setDetailAffectation(null);
                stockService.ajouterArticle(article);
            });
            affectationService.supprimerAffectation(id);
            return "Affectation bien supprimer";
        }catch (Exception e){
            e.printStackTrace();
            return  "Problème lors de supprission de cette affectation";
        }
    }

    @GetMapping("/{id}")
    public DetailAffectationComplet recupererDetailAffectation(@PathVariable long id){
        try{
            DetailAffectation detailAffectation = affectationService.recupererAffectationParId(id);
            DetailAffectationComplet detailAffectationComplet = new DetailAffectationComplet();
            detailAffectationComplet.setDetail(detailAffectation.getDetail());
            detailAffectationComplet.setId(detailAffectation.getId());
            detailAffectationComplet.setExploiteur(detailAffectation.getUtilisateur());
            detailAffectationComplet.setDuree(detailAffectation.getDuree());
            List<Article> articles = stockService.recupererParAffectations(id);
            articles.forEach(article -> {
                article.setFicheStock(null);
                article.setDetailAffectation(null);
                article.setMarche(null);
            });
            //System.out.println(stockService.recupererParAffectations(id).size());
            detailAffectationComplet.setArticles(articles);
            return detailAffectationComplet;
        }catch (Exception e){
            return  null;
        }
    }
    @Data
    @NoArgsConstructor
    static  class DetailAffectationComplet{
        private long id;
        private long duree;
        private String detail;
        private Utilisateur exploiteur;
        private List<Article> articles;
    }
}
