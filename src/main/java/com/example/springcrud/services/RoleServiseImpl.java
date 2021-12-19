package com.example.springcrud.services;

import com.example.springcrud.dao.RoleDAO;
import com.example.springcrud.entities.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiseImpl implements RoleServise{

    private RoleDAO roleDAO;
    public RoleServiseImpl(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    @Transactional
    public Role findRoleByString(String roleName) {
        return roleDAO.findRoleByString(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
