package com.example.springcrud.dao;

import com.example.springcrud.entities.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> getAll();
    Person select(Long id);
    void save(Person person);
    void delete(Long id);
    void update(Long id, Person person);
    List<Person> findPersonByRole(String roleName);
    Person findByUserName(String username);
}
