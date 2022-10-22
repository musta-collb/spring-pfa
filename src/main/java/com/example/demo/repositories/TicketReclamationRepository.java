package com.example.demo.repositories;

import com.example.demo.entities.TicketReclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketReclamationRepository extends JpaRepository<TicketReclamation, Long> {
}