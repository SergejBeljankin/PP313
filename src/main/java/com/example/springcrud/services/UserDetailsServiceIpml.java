package com.example.springcrud.services;

import com.example.springcrud.entities.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
public class UserDetailsServiceIpml implements UserDetailsService {
    private PersonServise personServise;


    public UserDetailsServiceIpml(PersonServise personServise){
        this.personServise = personServise;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personServise.findByUserName(s);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("User %s not found!", s));
        }
        return person;
    }

}
