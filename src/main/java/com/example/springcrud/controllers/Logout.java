package com.example.springcrud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Logout {

    @GetMapping("/login")
    public String logoutSucsess() {
        return "/login";
    }

}
