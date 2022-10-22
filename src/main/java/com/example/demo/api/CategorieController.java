package com.example.demo.api;

import com.example.demo.entities.Article;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.FicheStock;
import com.example.demo.services.StockService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class CategorieController {
    // n'oublie pas d'affecter les roles!!

    @Autowired
    private StockService stockService;

    @GetMapping(path= "/fichesStock")
    public List<FicheStock> recupererTous(){
        System.out.println("Get:ficheStock ");
        return stockService.recupererTous();
    }


    @PostMapping(path="/fichesStock")
    public ResponseEntity<String > ajouterCategorie(@RequestBody FicheStock categorie){
        System.out.println("Get:ficheStock ");
        if(stockService.trouverFiche(categorie.getDesignation()) == null){
            stockService.ajouterFiche(categorie);
            return ResponseEntity.ok().body("La categorie "+ categorie.getDesignation()+ " a été crée avec success!");
        }else{
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("La categorie exite déjà!");
        }
    }

    @GetMapping(path= "/fichesStock/{id}")
    public FicheStock trouverParId(@PathVariable Long id){
        FicheStock ficheStock = stockService.trouverParId(id);
        System.out.println(ficheStock.toString());
        if(ficheStock == null){
            return null;
        }
        return ficheStock;
    }

    @PutMapping(path = "/fichesStock/{id}")
    public ResponseEntity<String> modifierFicheStock(@PathVariable long id,@RequestBody FicheStock ficheStock){
        FicheStock ficheStock1 = stockService.trouverParId(id);
        FicheStock test = stockService.trouverFiche(ficheStock.getDesignation());
        if(test!= null && test.getId() != id){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Une categorie avec la meme designation existe déjà!");
        }
        ficheStock1.setDesignation(ficheStock.getDesignation());
        ficheStock1.setQuantiteDisponible(ficheStock.getQuantiteDisponible());
        ficheStock1.setQuantiteMax(ficheStock.getQuantiteMax());
        ficheStock1.setQuantiteMin(ficheStock.getQuantiteMin());
        stockService.ajouterFiche(ficheStock1);
        return ResponseEntity.ok().body("L'entité " + ficheStock.getDesignation()+ " a été mis à jour!!");
    }


    @GetMapping("/fichesStock/{idCat}/articles")
    public List<Article> getArticlesByCategorieStock(@PathVariable long idCat){
        FicheStock ficheStock = stockService.trouverParId(idCat);
        if(ficheStock != null){
            return ficheStock.getArticles();
        }
        return new ArrayList<>();
    }

    @GetMapping("/notifications")
    public List<Notification> getNotifications(){
        List<FicheStock> ficheStockList =stockService.recupererNotifications();
        List<Notification> notifications = new ArrayList<Notification>();
        ficheStockList.forEach(ficheStock -> {
            Notification notification = new Notification();
            notification.setCategorie(ficheStock.getDesignation());
            if(ficheStock.getQuantiteDisponible() > ficheStock.getQuantiteMax()){
                notification.setMessage("La quantité disponible est assez grand!");
                notification.setStatus("info");
            }else {
                notification.setMessage("La quantité disponible est inférieur à la quantité disponible");
                notification.setStatus("danger");
            }
            notifications.add(notification);
        });
        return notifications;
    }
    @Data
    @NoArgsConstructor
    static class Notification{
        private String categorie;
        private String status;
        private String message;
    }
}
