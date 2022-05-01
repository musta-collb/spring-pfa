package com.example.demo.api;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PersonnelController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @GetMapping(path = "/test")
    public void Test(String email){
       System.out.println(personnelRepository.findByEmailEquals(email).toString());

    }
}
