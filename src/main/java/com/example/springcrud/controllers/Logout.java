package com.example.springcrud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Logout {

    @GetMapping("/logoutsucsess")
    public String logoutSucsess() {
        return "/logoutsucsess";
    }

}
