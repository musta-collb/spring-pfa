package com.example.demo.services;

import com.example.demo.entities.DetailOffre;
import com.example.demo.entities.Offre;

import java.util.List;

public interface OffreService {
    public Offre ajouter(Offre offre);
    public DetailOffre ajouterDetailOffre(DetailOffre detailOffre);
    public List<Offre> recupererTous();
    public Offre recuperParId(long id);
    public DetailOffre recupererDetailParId(long id);
    public Offre tourverParFounisseurAppelOffre(long idFour, long idAppel);
}
