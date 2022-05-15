package com.example.demo.services;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
