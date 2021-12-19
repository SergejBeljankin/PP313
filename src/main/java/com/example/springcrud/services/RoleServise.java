package com.example.springcrud.services;


import com.example.springcrud.entities.Role;

import java.util.List;

public interface RoleServise {
    void save(Role role);
    Role findRoleByString(String roleName);
    List<Role> getAllRoles();
}
