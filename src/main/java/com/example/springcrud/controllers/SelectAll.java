package com.example.springcrud.controllers;


import com.example.springcrud.services.UserDetailsServiceIpml;
import com.example.springcrud.services.PersonServiseInterface;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SelectAll {
    private final PersonServiseInterface personServiseInterface;
    private final RoleServise roleServise;

    @Autowired
    public SelectAll(RoleServise roleServise,
                     PersonServiseInterface personServiseInterface) {
        this.personServiseInterface = personServiseInterface;
        this.roleServise = roleServise;
    }

    @GetMapping("/select_all")
    public String selectAll(Model model){
        model.addAttribute("people", personServiseInterface.getAll());
        return "/select_all";
    }

    @DeleteMapping("/select_all/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personServiseInterface.delete(id);
        return "redirect:/select_all";
    }
}
