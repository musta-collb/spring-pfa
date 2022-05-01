package com.example.demo.security;

import com.example.demo.entities.Personnel;
import com.example.demo.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private PersonnelRepository personnelRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Personnel personnel = personnelRepository.findByEmailEquals(email);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        personnel.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return new User(email, personnel.getPassword(),authorities);
    }
}
