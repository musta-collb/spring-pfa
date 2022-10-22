package com.example.demo.api;

import com.example.demo.entities.AppelOffre;
import com.example.demo.entities.Fournisseur;
import com.example.demo.entities.Marche;
import com.example.demo.entities.Offre;
import com.example.demo.services.AppelOffreService;
import com.example.demo.services.FournisseurService;
import com.example.demo.services.OffreService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.model.source.spi.EmbeddableMapping;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {
    @Autowired
    private FournisseurService fournisseurService;
    @Autowired
    private AppelOffreService appelOffreService;
    @Autowired
    private OffreService offreService;

    @GetMapping("")
    public List<Fournisseur> recupererTous(){
        List<Fournisseur> fournisseurs = fournisseurService.recupererTous();
        return fournisseurs;
    }
    @GetMapping("/{id}")
    public AboutFounisseur recupererParId(@PathVariable long id){
        AboutFounisseur aboutFounisseur =new AboutFounisseur();
        Fournisseur fournisseur = fournisseurService.recupererParId(id);
        aboutFounisseur.setFournisseur(fournisseur);
        aboutFounisseur.setNbMarches(fournisseur.getMarches().size());
        aboutFounisseur.setNbOffres(fournisseur.getOffres().size());
        return aboutFounisseur;
    }

    @PostMapping("/{id}")
    public String  updateFounisseur(@PathVariable long id, @RequestBody Fournisseur mutatedFounisseur){
        Fournisseur fournisseur = fournisseurService.recupererParId(id);
        fournisseur.setAdresse(mutatedFounisseur.getAdresse());
        fournisseur.setCodePostale(mutatedFounisseur.getCodePostale());
        fournisseur.setMail(mutatedFounisseur.getMail());
        fournisseur.setNbAnnees(mutatedFounisseur.getNbAnnees());
        fournisseur.setPays(mutatedFounisseur.getPays());
        fournisseur.setRaisonSociale(mutatedFounisseur.getRaisonSociale());
        fournisseur.setTel(mutatedFounisseur.getTel());
        fournisseurService.ajouterFournisseur(fournisseur);
        return "Le fournisseur a été modifié!";
    }

    @Data
    @NoArgsConstructor
    static class AboutFounisseur{
        private int nbMarches;
        private int nbOffres;
        private Fournisseur fournisseur;
    }
    //================================================Marche

    @GetMapping("/appelsOffres")
    public FournisseurAppelsOffres getFournisseursAppelsOffres(){
        FournisseurAppelsOffres fournisseurAppelsOffres = new FournisseurAppelsOffres();
        fournisseurAppelsOffres.setFournisseurs(fournisseurService.recupererTous());
        List<AppelOffre> appelOffres = appelOffreService.recupererTous();
        List<AppelsOffresReduits> appelsOffresReduitsList = new ArrayList<AppelsOffresReduits>();
        appelOffres.forEach(appelOffre -> {
           AppelsOffresReduits appelsOffresReduits = new AppelsOffresReduits();
           appelsOffresReduits.setReference(appelOffre.getReference());
           appelsOffresReduits.setDateCreation(appelOffre.getDateCreation());
           appelsOffresReduitsList.add(appelsOffresReduits);
        });
        fournisseurAppelsOffres.setAppelsOffres(appelsOffresReduitsList);
    return fournisseurAppelsOffres;
    }
    @Data
    @NoArgsConstructor
    static class FournisseurAppelsOffres{
        private List<AppelsOffresReduits> appelsOffres;
        private List<Fournisseur> fournisseurs;
    }
    @Data
    @NoArgsConstructor
    static class AppelsOffresReduits{
        private String reference;
        private Date dateCreation;
    }

    @PostMapping("/marches")
    public String ajouterMarche(@RequestBody MarcheInfo marcheInfo){
        System.out.println(marcheInfo.toString());
        Marche marche = new Marche();
        marche.setFournisseur(fournisseurService.recupererParId(marcheInfo.getIdFournisseur()));
        marche.setAppelOffre(appelOffreService.recupererParReference(marcheInfo.getReferenceAppel()));
        marche.setDateLivraison(marcheInfo.getDateLervaison());
        marche.setDateRealisation(marcheInfo.getDateRealisation());
        marche.setObjet(marcheInfo.getObjet());
        fournisseurService.ajouterMarche(marche);
        return "Un marche avec l'objet \""+ marcheInfo.getObjet() + "\" a été crée";
    }
    @Data
    @NoArgsConstructor
    static class MarcheInfo{
        private Date dateLervaison;
        private Date dateRealisation;
        private long idFournisseur;
        private String objet;
        private String referenceAppel;
    }

    @GetMapping("/marches/{id}")
    public MarcheDetails getDetailMarche(@PathVariable long id){
        MarcheDetails marcheDetails = new MarcheDetails();
        Marche marche = fournisseurService.recupererMarcheParId(id);
        if(marche == null){
            return null;
        }
        marcheDetails.setFournisseur(marche.getFournisseur());
        marcheDetails.setId(id);
        if(marche.getAppelOffre() !=null){
            marcheDetails.setReference(marche.getAppelOffre().getReference());
        }
        marcheDetails.setDateLivraison(marche.getDateLivraison());
        marcheDetails.setDateRealisation(marche.getDateRealisation());
        System.out.println("id fourni:"+ marche.getFournisseur().getId()+ "| id appelsOff:"+marche.getAppelOffre().getId());
        //
        List<DetailsM> detailsMS = new ArrayList<DetailsM>();
        Offre offre = offreService.tourverParFounisseurAppelOffre(marche.getFournisseur().getId(), marche.getAppelOffre().getId());
        if(offre == null || offre.getDetailOffres() == null){
            marcheDetails.setDetails(null);
        }
        else {
            offre.getDetailOffres().forEach(detailOffre -> {
                DetailsM detail = new DetailsM();
                detail.setDesignation(detailOffre.getArticleGenerique().getDesignation());
                detail.setQuantite(detailOffre.getQuantite());
                detail.setPrixUnitaire(detailOffre.getPrixUnitaire());
                detailsMS.add(detail);

            });
            marcheDetails.setDetails(detailsMS);
        }


        return marcheDetails;
    }

    @Data
    @NoArgsConstructor
    static class MarcheDetails{
        private long id;
        private Fournisseur fournisseur;
        private String reference;
        private Date dateRealisation;
        private Date dateLivraison;
        private List<DetailsM> details;
    }

    @Data
    @NoArgsConstructor
    static class DetailsM{
        private String designation;
        private double prixUnitaire;
        private double quantite;
    }

    @GetMapping("/marches")
    public List<MarcheReduit> getMarches(){
        List<Marche>  marches = fournisseurService.recupererTousMarche();
        if(marches==null){
            return new ArrayList<>();
        }
        List<MarcheReduit> marcheReduits = new ArrayList<MarcheReduit>();
        marches.forEach(marche -> {
            MarcheReduit marcheReduit = new MarcheReduit();
            marcheReduit.setFournisseur(marche.getFournisseur());
            marcheReduit.setReference(marche.getAppelOffre().getReference());
            marcheReduit.setDateRealisation(marche.getDateRealisation());
            marcheReduit.setId(marche.getId());
            marcheReduit.setObjet(marche.getObjet());
            marcheReduits.add(marcheReduit);
        });
        return marcheReduits;
    }
    @Data
    @NoArgsConstructor
    static class   MarcheReduit{
        private long id;
        private Fournisseur fournisseur;
        private String reference;
        private Date dateRealisation;
        private String objet;
    }

    @GetMapping("/fournisseursMarches")
    public List<FounisseursMarches> getFounisseursMarches(){
        List<Fournisseur> fournisseurs = fournisseurService.recupererTous();
        List<FounisseursMarches> founisseursMarches = new ArrayList<FounisseursMarches>();
        fournisseurs.forEach((fournisseur -> {
            FounisseursMarches founisseursMarches1 = new FounisseursMarches();
            founisseursMarches1.setFournisseur(fournisseur);
            founisseursMarches1.setMarches(fournisseur.getMarches());
            if(!fournisseur.getMarches().isEmpty()){
                founisseursMarches.add(founisseursMarches1);
            }
        }));
        return founisseursMarches;
    }
    @Data
    @NoArgsConstructor
    static class FounisseursMarches{
        private Fournisseur fournisseur;
        private List<Marche> marches;
    }
}
