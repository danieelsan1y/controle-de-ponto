package com.controledeponto.application.service;

import com.controledeponto.application.enums.StatusPerson;
import com.controledeponto.application.exceptions.service.ServiceException;
import com.controledeponto.application.model.Person;
import com.controledeponto.application.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void verifyUniqueElement(Person person) {
        Optional<Person> personExists = personRepository.findByLogin(person.getLogin());

        personExists.ifPresent(person1 -> {
            throw  new ServiceException("Login já cadastrado, por favor escolher outro!");
        });
    }


}
