package com.example.springcrud.restcontrollers;

import com.example.springcrud.entities.Person;
import com.example.springcrud.entities.Role;
import com.example.springcrud.exepion_handling.NoSuchPersonException;
import com.example.springcrud.exepion_handling.PersonIncorrectDate;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Person person = new Person();
        try {
             person = personServise.select(id);
        } catch (Exception e) {
            System.out.println("Тута ексепшен при id = " + id);
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
//    @PostMapping("/persons/info")
//    public Person personInfo(@RequestBody String username){
//        return personServise.findByUserName(username);
//    }
}
