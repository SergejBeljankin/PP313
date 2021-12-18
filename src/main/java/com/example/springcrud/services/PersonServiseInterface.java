package com.example.springcrud.services;


import com.example.springcrud.entities.Person;

import java.util.List;

public interface PersonServiseInterface {
    List<Person> getAll();
    Person select(Long id);
    void save(Person person);
    void delete(Long id);
    void update(Long id, Person person);
    Person findByUserName(String username);
}
