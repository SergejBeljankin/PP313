package com.example.springcrud.services;

import com.example.springcrud.dao.PersonDAO;
import com.example.springcrud.entities.Person;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class PersonServiseImpl implements PersonServiseInterface{

    private PersonDAO personDAO;

    private BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    public PersonServiseImpl(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @Override
    public Person select(Long id) {
        return personDAO.select(id);
    }

    @Override
    public void save(Person person) {
        person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));
        personDAO.save(person);
    }

    @Override
    public void delete(Long id) {
        personDAO.delete(id);
    }


    @Override
    public void update(Long id, Person person) {
        person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));
        personDAO.update(id, person);
    }

    @Override
    public List<Person> getAll() {
        return personDAO.getAll();
    }

    @Override
    @Transactional
    public Person findByUserName(String username) {
        return personDAO.findByUserName(username);
    }
}
