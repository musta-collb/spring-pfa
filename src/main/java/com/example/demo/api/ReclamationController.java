package com.example.demo.api;

import com.example.demo.entities.Commantaire;
import com.example.demo.entities.TicketReclamation;
import com.example.demo.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;
    @GetMapping("")
    public List<TicketReclamation> recupererTous(){
        List<TicketReclamation> tickets = reclamationService.recupererTous();
        tickets.forEach(ticket->{
            ticket.getArticle().setMarche(null);
            ticket.getPersonnel().setRoles(null);
            ticket.getPersonnel().setUtilisateur(null);
            ticket.getPersonnel().setPassword(null);
            ticket.getPersonnel().setTicketReclamations(null);
        });
        return  tickets;
    }
    @GetMapping("/{id}")
    public TicketReclamation recupererParId(@PathVariable long id){
        try{
            TicketReclamation ticket = reclamationService.recupererParId(id);
            ticket.getArticle().setMarche(null);
            ticket.getPersonnel().setRoles(null);
            ticket.getPersonnel().setUtilisateur(null);
            ticket.getPersonnel().setPassword(null);
            ticket.getPersonnel().setTicketReclamations(null);
            return ticket;
        }catch (Exception e){
            return null;
        }
    }
    @PostMapping("/{id}/commentaires")
    public String ajouterCommantaire(@RequestBody Commantaire commantaire, @PathVariable long id){
        System.out.println(id);
        System.out.println(commantaire.getCommantaire());
        return "Yes";
    }
}
