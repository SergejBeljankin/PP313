package com.example.springcrud.restcontrollers;

import com.example.springcrud.entities.Person;
import com.example.springcrud.exepion_handling.NoSuchPersonException;
import com.example.springcrud.exepion_handling.PersonIncorrectDate;
import com.example.springcrud.services.PersonServise;
import com.example.springcrud.services.RoleServise;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private PersonServise personServise;

    @Autowired
    private RoleServise roleServise;

    @GetMapping("/persons")
    public List<Person> getAllPersonRequest(){
        List<Person> personList =  personServise.getAll();
        return personList;
    }

    @GetMapping("/persons/{id}")
    public Person selectPerson(@PathVariable Long id){
        Person person = new Person();
        try {
             person = personServise.select(id);
        } catch (Exception e) {
           throw new NoSuchPersonException("No person whis id = " + id + " in DataBase");
        }

        return person;
    }

    @ExceptionHandler
    public ResponseEntity<PersonIncorrectDate> handlerException(NoSuchPersonException exception){
        PersonIncorrectDate data = new PersonIncorrectDate();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/persons")
    public Person addNewPerson(@RequestBody Person person){
        personServise.save(person);
        return person;
    }

    @PutMapping("/persons")
    public Person updatePerson(@RequestBody Person person){
        Long id = person.getId();
        personServise.update( id, person);
        return personServise.select(id);
    }

    @DeleteMapping("/persons/{id}")
    public String deletePerson(@PathVariable int id){
        personServise.delete((long) id);
        return "Person with " +  id + " removed";
    }

    @GetMapping("/info")

    public Person showUserInfo(@AuthenticationPrincipal Person person) {
        Person personByUsername = personServise.findByUserName(person.getUsername());
        System.out.println(personByUsername);
        return personByUsername;
    }

}
