package com.example.springcrud.controllers;


import com.example.springcrud.entities.Person;
import com.example.springcrud.entities.Role;
import com.example.springcrud.services.UserDetailsServiceIpml;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class SelectAll {
    private final PersonServiseInterface personServiseInterface;
    private final RoleServise roleServise;

    @Autowired
    public SelectAll(RoleServise roleServise,
                     PersonServiseInterface personServiseInterface) {
        this.personServiseInterface = personServiseInterface;
        this.roleServise = roleServise;
    }

    @GetMapping("/select_all")
    public String selectAll(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person personAuthetication = (Person) auth.getPrincipal();
        model.addAttribute("roles", roleServise.getAllRoles());
        model.addAttribute("personAuthetication", personAuthetication);
        return "/select_all";
    }

}
