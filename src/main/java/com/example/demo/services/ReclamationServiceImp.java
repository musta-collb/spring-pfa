package com.example.demo.services;

import com.example.demo.entities.TicketReclamation;
import com.example.demo.repositories.TicketReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ReclamationServiceImp implements ReclamationService{
    @Autowired
    public TicketReclamationRepository ticketReclamationRepository;
    @Override
    public List<TicketReclamation> recupererTous() {
        return ticketReclamationRepository.findAll();
    }

    @Override
    public TicketReclamation recupererParId(long id) {
        return ticketReclamationRepository.getById(id);
    }
}
