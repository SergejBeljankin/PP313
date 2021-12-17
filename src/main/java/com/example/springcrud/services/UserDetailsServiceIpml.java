package com.example.springcrud.services;

import com.example.springcrud.dao.PersonDAO;
import com.example.springcrud.entities.Person;
import com.example.springcrud.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceIpml implements UserDetailsService {
    private PersonServiseInterface personServiseInterface;


    public UserDetailsServiceIpml(PersonServiseInterface personServiseInterface){
        this.personServiseInterface = personServiseInterface;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personServiseInterface.findByUserName(s);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User %s not found!", s));
        }
        return person;
    }

}
