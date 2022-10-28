package com.controledeponto.service;

import com.controledeponto.enums.AcessPerson;
import com.controledeponto.enums.StatusPerson;
import com.controledeponto.model.Person;
import com.controledeponto.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends GenericCrudService<Person, Long> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public JpaRepository<Person, Long> getRepository() {
        return this.personRepository;
    }

    @Override
    public void initInsert(Person person) {
        person.setStatus(StatusPerson.ACTIVE);
    }


}
