package com.example.springcrud.controllers;


import com.example.springcrud.entities.Person;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.UserDetailsServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
public class MainController {
    private PersonServiseInterface personServiseInterface;

    @Autowired
    public void setUserService(PersonServiseInterface personServiseInterface){
        this.personServiseInterface = personServiseInterface;
    }


//    @GetMapping("/")
//    public String homePage(){
//        return "home";
//    }
    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal){
        Person person = personServiseInterface.findByUserName(principal.getName());
        return  "Защищено от доступа <br>рады вас приветствовать: " + person.getUsername() + " " + person.getEmail();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile(){
        return "read profile page";
    }

    @GetMapping("/only_for_admins")
    public String pageOnlyForAdmins(){
        return "admins page";
    }
}
