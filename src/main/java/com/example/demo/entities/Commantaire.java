package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commantaire")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "ticketReclamation"})
public class Commantaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ticket_reclamation_id")
    private TicketReclamation ticketReclamation;

    public Long getId() {
        return id;
    }
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    private String commantaire;

    public TicketReclamation getTicketReclamation() {
        return ticketReclamation;
    }

    public void setTicketReclamation(TicketReclamation ticketReclamation) {
        this.ticketReclamation = ticketReclamation;
    }
}