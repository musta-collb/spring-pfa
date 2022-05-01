package com.example.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "commantaire")
@Data
@NoArgsConstructor
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