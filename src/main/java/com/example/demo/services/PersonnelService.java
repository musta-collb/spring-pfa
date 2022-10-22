package com.example.demo.services;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;

import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

public interface PersonnelService {
    public Personnel trouverParEmail(String email);
    public Personnel trouverParEmailMotDePasse(String email,String password);
    public Role tourverRole(String role);
    public void supprimmerPersonnel(Personnel personnel);
    public Personnel ajouterPersonnel(Personnel personnel);
    public Personnel supprimmerRolePersonnel(Personnel personnel, Role role);
    public List<Personnel> trouverTous();

    Personnel trouverParId(long id);
    public Role ajouterRole(Role role);
    List<Personnel> trouverParSearch(String search);
}
