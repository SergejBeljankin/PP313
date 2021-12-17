package com.example.springcrud.dao;


import com.example.springcrud.entities.Role;

public interface RoleDAO {
    void save(Role role);
    Role findRoleByString(String roleName);
}
