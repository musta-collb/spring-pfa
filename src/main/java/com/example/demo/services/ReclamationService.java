package com.example.demo.services;

import com.example.demo.entities.TicketReclamation;

import java.util.List;

public interface ReclamationService {
    public List<TicketReclamation> recupererTous();
    public TicketReclamation recupererParId(long id);

}
