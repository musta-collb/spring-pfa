package com.example.demo.services;

import com.example.demo.entities.DetailOffre;
import com.example.demo.entities.Offre;
import com.example.demo.repositories.DetailOffreRepo;
import com.example.demo.repositories.OffreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OffreServiceImp implements OffreService{
    @Autowired
    private OffreRepo offreRepo;
    @Autowired
    private DetailOffreRepo detailOffreRepo;

    @Override
    public Offre ajouter(Offre offre) {
        return offreRepo.save(offre);
    }

    @Override
    public DetailOffre ajouterDetailOffre(DetailOffre detailOffre) {
        return detailOffreRepo.save(detailOffre);
    }

    @Override
    public List<Offre> recupererTous() {
        return offreRepo.findAll();
    }

    @Override
    public Offre recuperParId(long id) {
        return offreRepo.getById(id);
    }

    @Override
    public DetailOffre recupererDetailParId(long id) {
        return detailOffreRepo.getById(id);
    }

    @Override
    public Offre tourverParFounisseurAppelOffre(long idFour, long idAppel) {
        return offreRepo.findByFournisseur_IdEqualsAndAppelOffre_IdEquals(idFour, idAppel);
    }
}
