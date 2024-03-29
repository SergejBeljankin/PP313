package com.example.springcrud.controllers;


import com.example.springcrud.entities.Person;
import com.example.springcrud.services.PersonServise;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SelectAll {
    private final RoleServise roleServise;

    @Autowired
    public SelectAll(RoleServise roleServise) {
        this.roleServise = roleServise;
    }

    @GetMapping("/select_all")
    public String selectAll(Model model){
        model.addAttribute("roles", roleServise.getAllRoles());
        return "/select_all";
    }

}
