package com.example.demo.helpers;

import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class OnStartServer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    PersonnelService personnelService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String [] roles = {"ADMIN","RRECLAMATION","RSTOCK","RFOURNISSEUR","RREBUT", "RAFFECTATION","UTILISATEUR"};
        Arrays.stream(roles).forEach(role->{
            if(personnelService.tourverRole(role) == null){
                Role role1 = new Role();
                role1.setRole(role);
                personnelService.ajouterRole(role1);
            }
        });
        if(personnelService.trouverParEmail("exemple@gmail.com") == null){
            Personnel personnel = new Personnel();
            personnel.setEmail("exemple@gmail.com");
            personnel.setNom("User");
            personnel.setPrenom("Admin");
            personnel.setFonction("Admin");
            personnel.setPassword(bCryptPasswordEncoder.encode("123456"));
            personnelService.ajouterPersonnel(personnel);


            List<Role> roleList = new ArrayList<Role>();
            Arrays.stream(roles).forEach(role->{
                personnel.getRoles().add(personnelService.tourverRole(role));
            });
            System.out.println("The problem is here" +
                    "");
            personnelService.ajouterPersonnel(personnel);


        }
    }
}
