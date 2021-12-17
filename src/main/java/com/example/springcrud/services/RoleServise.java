package com.example.springcrud.services;


import com.example.springcrud.entities.Role;

public interface RoleServise {
    void save(Role role);
    Role findRoleByString(String roleName);
}
