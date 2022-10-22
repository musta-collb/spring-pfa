package com.example.demo.security;

import com.example.demo.entities.Personnel;
import com.example.demo.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserDetailServiceImp implements UserDetailsService {
    //@Autowired
    //private PersonnelRepository personnelRepository;
    @Autowired
    private PersonnelService personnelService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            System.out.println("---userDeait service");
            //Personnel personnel = personnelRepository.findByEmailEquals(email);
            Personnel personnel = personnelService.trouverParEmail(email);
            if(personnel == null){
                throw new UsernameNotFoundException("Utilisateur non existant!");
            }
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            personnel.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            });
            return new User(email, personnel.getPassword(),authorities);

    }
}
