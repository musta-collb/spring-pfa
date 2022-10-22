package com.example.demo.services;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImp implements PersonnelService {
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Personnel trouverParEmail(String email) {
        return personnelRepository.findByEmailEquals(email);
    }

    @Override
    public Personnel trouverParEmailMotDePasse(String email, String password) {
        return personnelRepository.findByEmailEqualsAndPasswordEquals(email, password);
    }

    @Override
    public Role tourverRole(String role) {
        return roleRepository.findByRoleEquals(role);
    }

    @Override
    public void supprimmerPersonnel(Personnel personnel) {
        personnelRepository.delete(personnel);
    }

    @Override
    public Personnel ajouterPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public Personnel supprimmerRolePersonnel(Personnel personnel, Role role) {
        return null;
    }

    @Override
    public List<Personnel> trouverTous() {
        return personnelRepository.findAll();
    }

    @Override
    public Personnel trouverParId(long id) {
        return personnelRepository.getById(id);
    }

    @Override
    public Role ajouterRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Personnel> trouverParSearch(String search) {
        return personnelRepository.findBySearch(search);
    }


}
