package com.example.springcrud.dao;


import com.example.springcrud.entities.Role;

import java.util.List;

public interface RoleDAO {
    void save(Role role);
    Role findRoleByString(String roleName);
    List<Role> getAllRoles();
}
