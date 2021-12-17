package com.example.springcrud.controllers;

import com.example.springcrud.entities.Person;
import com.example.springcrud.services.UserDetailsServiceIpml;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private final PersonServiseInterface personServiseInterface;


    @Autowired
    public UserController(PersonServiseInterface personServiseInterface) {
        this.personServiseInterface = personServiseInterface;

    }

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") int id){
        Person person = personServiseInterface.select(id);
        model.addAttribute("user", personServiseInterface.select(id));
        return "/user";
    }
}
