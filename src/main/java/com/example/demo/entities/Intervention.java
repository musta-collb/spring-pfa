package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "intervention")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "ticketReclamation"})
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_reclamation_id")
    private TicketReclamation ticketReclamation;

    private String intervention;
    private Date date;
}