package com.example.springcrud.controllers;


import com.example.springcrud.entities.Person;
import com.example.springcrud.entities.Role;
import com.example.springcrud.services.UserDetailsServiceIpml;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String selectAll(@ModelAttribute("newPerson") Person person, Model model){
        model.addAttribute("people", personServiseInterface.getAll());
        return "/select_all";
    }

    @DeleteMapping("/select_all/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        personServiseInterface.delete(id);
        return "redirect:/select_all";
    }

    @PostMapping("/select_all")
    public String create(@ModelAttribute("newPerson") Person person
            , @RequestParam("rolesNames") String[] rolesNames){

        Set<Role> roleSet = new HashSet<>();
        if(rolesNames.length !=0){
            for (String role: rolesNames) {
                roleSet.add(roleServise.findRoleByString(role));
            }
        } else {
            roleSet.add(roleServise.findRoleByString("ROLE_USER"));
        }
        person.setRoles(roleSet);
        personServiseInterface.save(person);
        return "redirect:/select_all";
    }
}
