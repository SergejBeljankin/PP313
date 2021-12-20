package com.example.springcrud.restcontrollers;

import com.example.springcrud.entities.Person;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private PersonServiseInterface personServise;

    @Autowired
    private RoleServise roleServise;

    @GetMapping("/persons")
    public List<Person> getAllPersonRequest(){
        List<Person> personList =  personServise.getAll();
        return personList;
    }

    @GetMapping("/persons/{id}")
    public Person selectPerson(@PathVariable Long id){

        return personServise.select(id);
    }
}
