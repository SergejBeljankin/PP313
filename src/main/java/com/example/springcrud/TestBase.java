package com.example.springcrud;

import com.example.springcrud.entities.Person;
import com.example.springcrud.entities.Role;
import com.example.springcrud.services.PersonServise;
import com.example.springcrud.services.RoleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestBase {
    private final PersonServise personServise;
    private final RoleServise roleServise;

    @Autowired
    public TestBase(PersonServise personServise, RoleServise roleServise){
        this.personServise = personServise;
        this.roleServise = roleServise;

    }
    @PostConstruct
    public void dataInitializer() {
        Person person1 = new Person("p.ivanov@gmail.com","100", "Петр", "Иванов", 20);
        Person person2 = new Person("i.petrov@yandex.ru", "100", "Иван", "Петров", 25);
        Person person3 = new Person("s.sidorov@bk.ru", "100", "Семен", "Сидоров", 27);

        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        Role role3 = new Role("ROLE_MANAGER");

        roleServise.save(role1);
        roleServise.save(role2);
        roleServise.save(role3);

        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);

        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(role2);
        roleSet2.add(role3);

        Set<Role> roleSet3 = new HashSet<>();
        roleSet3.add(role3);

        person1.setRoles(roleSet1);
        person2.setRoles(roleSet2);
        person3.setRoles(roleSet3);


        personServise.save(person1);
        personServise.save(person2);
        personServise.save(person3);
    }
}
