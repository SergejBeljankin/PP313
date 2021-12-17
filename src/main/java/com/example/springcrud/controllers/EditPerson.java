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
public class EditPerson {
    private final UserDetailsServiceIpml userDetailsServiceIpml;
    private final PersonServiseInterface personServiseInterface;
    private final RoleServise roleServise;

    @Autowired
    public EditPerson(UserDetailsServiceIpml personServise, RoleServise roleServise,
                      PersonServiseInterface personServiseInterface) {
        this.userDetailsServiceIpml = personServise;
        this.personServiseInterface = personServiseInterface;
        this.roleServise = roleServise;
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id,
                         @RequestParam("rolesNames") String[] rolesNames) {
        Set<Role> roleSet = new HashSet<>();
        if(rolesNames.length !=0){
            for (String role: rolesNames) {
                roleSet.add(roleServise.findRoleByString(role));
            }
        } else {
            roleSet.add(roleServise.findRoleByString("ROLE_USER"));
        }
        person.setRoles(roleSet);
        personServiseInterface.update(id, person);
        return "redirect:/select_all";
    }



    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personServiseInterface.select(id));
        return "/edit";
    }

}
