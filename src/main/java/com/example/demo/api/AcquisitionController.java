package com.example.demo.api;

import com.example.demo.entities.*;
import com.example.demo.services.AppelOffreService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BinaryOperator;

@RestController
@RequestMapping(path = "/api/acquisition")
public class AcquisitionController {
    @Autowired
    private AppelOffreService appelOffreService;

    @GetMapping("/appelsOffres")
    public List<AppelOffre> getAll(){
//        System.out.println("called !!!!!");
//        AppelOffre appelOffre = new AppelOffre(34, "test appel d'offre");
//        appelOffre.setObjet("test");
//        appelOffre.setReference("REF3000");
//        List<AppelOffre>  appels = new ArrayList<AppelOffre>();
//        appels.add(appelOffre);

        List<AppelOffre> appels = new ArrayList<AppelOffre>();
        appels =  appelOffreService.recupererTous();
//        System.out.println(appels.toString());
        return  appels;
    }

    @PostMapping("/appelsOffres")
    public ResponseEntity<String> addAppel(@RequestBody AppelOffreProto apples){
        System.out.println(apples);
        AppelOffre appelOffre = new AppelOffre();
        appelOffre.setReference(apples.getReference());
        appelOffre.setBudget(apples.getBudget());
        appelOffre.setDescription(apples.getDescription());
        appelOffre.setObjet(apples.getObjet());
        appelOffre.setDateCreation(new Date());
        appelOffre.setDateLimite(apples.getDateLimite());

        appelOffreService.ajouterAppelOffre(appelOffre);

        apples.biens.forEach(bien -> {
            ArticleGenerique articleGenerique = new ArticleGenerique();
            articleGenerique.setDescription(bien.getDescriptionBien());
            articleGenerique.setDesignation(bien.getDesignation());
            articleGenerique.setUnite(bien.getUnite());

            appelOffreService.ajouterArcticle(articleGenerique);

            Quantite quantite = new Quantite();
            quantite.setQuantite(bien.getQuantite());
            quantite.setAppelOffre(appelOffre);
            quantite.setArticleGenerique(articleGenerique);

            appelOffreService.ajouterQuantite(quantite);
        });

        return ResponseEntity.ok().body("bien crée");
    }
// A changer par post car Put pose le problème de preflights
    @PostMapping("/appelsOffres/{id}")
    public String updateAppelOffre(@RequestBody AppelOffreProto apples,@PathVariable long id){
        System.out.println(id);

        AppelOffre appelOffre = appelOffreService.reccupererParId(id);
        if(apples.getReference() != "" && apples.getReference() != null){
            appelOffre.setReference(apples.getReference());
        }
        if(apples.getBudget() != .0f){
            appelOffre.setBudget(apples.getBudget());
        }
        if(apples.getObjet() != "" && apples.getObjet() != null){
            appelOffre.setObjet(apples.getObjet());
        }
        if(apples.getDateLimite() != null){
            appelOffre.setDateLimite(apples.getDateLimite());
        }
        if(apples.getDescription() != ""){
            appelOffre.setDescription(apples.getDescription());
        }

        //System.out.println(appelOffre);
        //appelOffre.setDateCreation(new Date());


        //appelOffreService.ajouterAppelOffre(appelOffre);

        apples.biens.forEach(bien -> {
            ArticleGenerique articleGenerique;
            Quantite quantite;
                if(bien.getId() != 0){
                    articleGenerique = appelOffreService.getArticleGeneriqueParId(bien.getId());
                    quantite = appelOffreService.trouverParAppelOffreArticleGenr(bien.getId(), appelOffre.getId());
                }else {
                    articleGenerique = new ArticleGenerique();
                    quantite = new Quantite();
                }
                articleGenerique.setDescription(bien.getDescriptionBien());
                articleGenerique.setDesignation(bien.getDesignation());
                articleGenerique.setUnite(bien.getUnite());

                appelOffreService.ajouterArcticle(articleGenerique);
                System.out.println(articleGenerique.getId());
                quantite.setQuantite(bien.getQuantite());
                quantite.setAppelOffre(appelOffre);
                quantite.setArticleGenerique(articleGenerique);

                appelOffreService.ajouterQuantite(quantite);


        });
        return  "Bien";
    }



    public void hleper(AppelOffre appelOffre, AppelOffreProto appelOffreProto){

    }
    @NoArgsConstructor
    @Data
    static class AppelOffreProto {
        private Long id;
        private double budget;
        private Date dateLimite;
        private String description;
        private String objet;
        private String reference;
        private List<Bien> biens;
    }

    @NoArgsConstructor
    @Data
    static class Bien {
        private long id;
        private String descriptionBien;

        private String designation;

        private double quantite;

        private String unite;
    }


    @GetMapping("/appelsOffres/references")
    public List<AppelOffre> recupererReferences(){
        List<AppelOffre> appels = new ArrayList<AppelOffre>();
        appels =  appelOffreService.recupererTous();
//        appels.forEach(appelOffre -> {
//            appelOffre.setQuantites(null);
//        });
        return appels;
    }

    @GetMapping("/fournisseurs")
    public  List<Fournisseur> getAllFournisseur(){
        return new ArrayList<>();
    }

    @GetMapping("/appelsOffres/{id}")
    public  AppelOffreProto RecupererAppelOffreParId(@PathVariable long id){
        //System.out.println(appelOffreService.reccupererParId(id).toString());
        AppelOffreProto appelOffreProto = new AppelOffreProto();
        AppelOffre appelOffre = appelOffreService.reccupererParId(id);

        appelOffreProto.setDateLimite(appelOffre.getDateLimite());
        appelOffreProto.setDescription(appelOffre.getDescription());
        appelOffreProto.setObjet(appelOffre.getObjet());
        appelOffreProto.setReference(appelOffre.getReference());
        appelOffreProto.setBudget(appelOffre.getBudget());
        appelOffreProto.setId(appelOffre.getId());
        List<Bien> biens = new ArrayList<>();
        appelOffre.getQuantites().forEach((quantite -> {
            Bien bien =new Bien();
            System.out.println(quantite.getArticleGenerique().getUnite());
            bien.setDescriptionBien(quantite.getArticleGenerique().getDescription());
            bien.setQuantite(quantite.getQuantite());
            bien.setId(quantite.getArticleGenerique().getId());
            bien.setDesignation(quantite.getArticleGenerique().getDesignation());
            bien.setUnite(quantite.getArticleGenerique().getUnite());
            biens.add(bien);
        }));
        appelOffreProto.setBiens(biens);
        //appelOffreService.reccupererParId(id)
        return appelOffreProto ;
    }


    @GetMapping("/offres")
    public List<Offre> getOffres(){
        return  new ArrayList<>();
    }

    @DeleteMapping("/appelsOffres/{id}")
    public  String supprimmerAppelOffre(@PathVariable long id){
        appelOffreService.supprimerAppelOffre(id);
        return "Appel d'offre bien supprimer!";
    }

}
