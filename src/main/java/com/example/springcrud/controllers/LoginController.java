package com.example.springcrud.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/hello")
    public String hello(Model model){
        String message = "Hello";
        model.addAttribute("message", message);
        return "/hello";
    }
}
