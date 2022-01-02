package com.example.springcrud.controllers;

import com.example.springcrud.entities.Person;
import com.example.springcrud.services.PersonServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private final PersonServise personServise;


    @Autowired
    public UserController(PersonServise personServise) {
        this.personServise = personServise;

    }

    @GetMapping("/{id}")
    public String userPage(Model model, @PathVariable("id") Long id){
        Person person = personServise.select(id);
        model.addAttribute("user", personServise.select(id));
        return "/user";
    }
}
