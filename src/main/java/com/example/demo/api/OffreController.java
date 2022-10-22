package com.example.demo.api;

import com.example.demo.entities.*;
import com.example.demo.services.AppelOffreService;
import com.example.demo.services.FournisseurService;
import com.example.demo.services.OffreService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/offres")
public class OffreController {
    @Autowired
    private AppelOffreService appelOffreService;
    @Autowired
    private FournisseurService fournisseurService;
    @Autowired
    private OffreService offreService;

    @PostMapping(value = "")
    public ResponseEntity<String> ajouterOffre(@RequestBody OffreProto offreProto){
        System.out.println(offreProto.toString());
        AppelOffre appelOffre = appelOffreService.recupererParReference(offreProto.reference);
        Fournisseur fournisseur = fournisseurService.ajouterFournisseur(offreProto.getFournisseur());
        Offre offre = new Offre();
        offre.setAppelOffre(appelOffre);
        offre.setDateCreation(new Date());
        offre.setFournisseur(fournisseur);
        offreService.ajouter(offre);
        offreProto.getBiens().forEach((bien)->{
            DetailOffre  detailOffre= new DetailOffre();
            detailOffre.setOffre(offre);
            detailOffre.setDateCreation(new Date());
            detailOffre.setArticleGenerique(appelOffreService.getArticleGeneriqueParId(bien.getId()));
            detailOffre.setQuantite(bien.getQuantite());
            detailOffre.setMarque(bien.marque);
            detailOffre.setDetail(bien.getDetails());
            detailOffre.setPrixUnitaire(bien.getPrixUnitaire());
            offreService.ajouterDetailOffre(detailOffre);
        });
        return ResponseEntity.ok().body("hello wold");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<SimpleOffre>> recupererOffres(){
        List<Offre> offres = offreService.recupererTous();
        System.out.println(offres.size());
        List<SimpleOffre> simpleOffres = new ArrayList<SimpleOffre>();

        offres.forEach((offre -> {
            SimpleOffre simpleOffre = new SimpleOffre();
            simpleOffre.setFournisseur(offre.getFournisseur());
            simpleOffre.setDate(offre.getDateCreation());
            simpleOffre.setId(offre.getId());
            simpleOffre.setRefrence(offre.getAppelOffre().getReference());
            simpleOffres.add(simpleOffre);
        }));
        return ResponseEntity.ok().body(simpleOffres);
    }

    @GetMapping("/{id}")
    public  DetailOffreCompose recuperDetailParId(@PathVariable long id){
        Offre offre = offreService.recuperParId(id);
        Fournisseur fournisseur = offre.getFournisseur();
        //
        DetailOffreCompose detailOffreCompose = new DetailOffreCompose();
        detailOffreCompose.setId(offre.getId());
        detailOffreCompose.setFournisseur(fournisseur);
        detailOffreCompose.setReference(offre.getAppelOffre().getReference());
        detailOffreCompose.setDate(offre.getDateCreation());
        //
        List<BienDetailOffreComppose> biens = new ArrayList<BienDetailOffreComppose>();
        List<DetailOffre> detailOffres = offre.getDetailOffres();
        detailOffres.forEach((detailOffre -> {
            BienDetailOffreComppose bien = new BienDetailOffreComppose();
            bien.setDetail(detailOffre.getDetail());
            bien.setMarque(detailOffre.getMarque());
            bien.setQuantite(detailOffre.getQuantite());
            bien.setPrixUnitaire(detailOffre.getPrixUnitaire());
            bien.setId(detailOffre.getId());
            //
            bien.setDesignation(detailOffre.getArticleGenerique().getDesignation());
            bien.setUnite(detailOffre.getArticleGenerique().getUnite());
            biens.add(bien);
        }));
        detailOffreCompose.setBiens(biens);
        return detailOffreCompose;
    }

    @PostMapping("/{id}")
    public String modifierOffre(@PathVariable long id,@RequestBody DetailOffreCompose detailOffreCompose){
        //System.out.println(detailOffreCompose.toString());
        Fournisseur mutatedFounisseur = detailOffreCompose.getFournisseur();
        Fournisseur fournisseur = fournisseurService.recupererParId(mutatedFounisseur.getId());
        //fournisseur.setId(mutatedFounisseur.);
        fournisseur.setAdresse(mutatedFounisseur.getAdresse());
        fournisseur.setCodePostale(mutatedFounisseur.getCodePostale());
        fournisseur.setMail(mutatedFounisseur.getMail());
        fournisseur.setNbAnnees(mutatedFounisseur.getNbAnnees());
        fournisseur.setPays(mutatedFounisseur.getPays());
        fournisseur.setRaisonSociale(mutatedFounisseur.getRaisonSociale());
        fournisseur.setTel(mutatedFounisseur.getTel());
        fournisseurService.ajouterFournisseur(fournisseur);
        //
        detailOffreCompose.getBiens().forEach(bien->{
            if(bien.getId() != 0l){
                DetailOffre detailOffre = offreService.recupererDetailParId(bien.getId());
                detailOffre.setQuantite(bien.getQuantite());
                detailOffre.setDetail(bien.getDetail());
                detailOffre.setPrixUnitaire(bien.getPrixUnitaire());
                detailOffre.setMarque(bien.marque);

                offreService.ajouterDetailOffre(detailOffre);

                ArticleGenerique articleGenerique = detailOffre.getArticleGenerique();
                articleGenerique.setDesignation(bien.designation);
                articleGenerique.setUnite(bien.getUnite());
                appelOffreService.ajouterArcticle(articleGenerique);
            }
        });

        return "Yes";
    }

    @Data
    @NoArgsConstructor
    static class DetailOffreCompose{
        private long id;
        private Fournisseur fournisseur;
        private String reference;
        private Date date;
        private List<BienDetailOffreComppose> biens;
    }

    @Data
    @NoArgsConstructor
    static class BienDetailOffreComppose{
        private long id;
        private String designation;
        private String marque;
        private  double quantite;
        private String unite;
        private double prixUnitaire;
        private String detail;
    }

    @Data
    @NoArgsConstructor
    static class SimpleOffre{
        private long id;
        private String refrence;
        private Date date;
        private Fournisseur fournisseur;
    }


    @Data
    @NoArgsConstructor
    static class OffreProto {
        private Fournisseur fournisseur;
        private List<BienOffre> biens;
        private String reference;
    }


    @NoArgsConstructor
    @Data
    static class  BienOffre{
       private long id;
       private String marque;
       private double prixUnitaire;
       private double quantite;
       private String details;
    }
}

