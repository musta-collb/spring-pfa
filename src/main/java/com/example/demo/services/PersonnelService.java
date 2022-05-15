package com.example.demo.services;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;

public interface PersonnelService {
    public Personnel trouverParEmail(String email);
    public Personnel trouverParEmailMotDePasse(String email,String password);
    public Role tourverRole(String role);
}
