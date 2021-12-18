package com.example.springcrud.controllers;


import com.example.springcrud.entities.Person;
import com.example.springcrud.entities.Role;
import com.example.springcrud.services.UserDetailsServiceIpml;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class NewPerson {
    private final PersonServiseInterface personServiseInterface;
    private final RoleServise roleServise;

    @Autowired
    public NewPerson(RoleServise roleServise,
                     PersonServiseInterface personServiseInterface) {
        this.personServiseInterface = personServiseInterface;
        this.roleServise = roleServise;
    }

//    @PostMapping("new_person")
//    public String create(@ModelAttribute("newPerson") Person person
//            , @RequestParam("rolesNames") String[] rolesNames){
//
//        Set<Role> roleSet = new HashSet<>();
//        if(rolesNames.length !=0){
//            for (String role: rolesNames) {
//                roleSet.add(roleServise.findRoleByString(role));
//            }
//        } else {
//            roleSet.add(roleServise.findRoleByString("ROLE_USER"));
//        }
//        person.setRoles(roleSet);
//        personServiseInterface.save(person);
//        return "redirect:/select_all";
//    }
//    @GetMapping("new_person")
//    public String newPerson(@ModelAttribute("newPerson") Person person){
//        return "new_person";
//    }
}
