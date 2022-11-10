package com.controledeponto.application.service;

import com.controledeponto.application.enums.StatusPerson;
import com.controledeponto.application.exceptions.service.ServiceException;
import com.controledeponto.application.message.Messages;
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


    public Person findByLogin(String login) {
        return Optional.ofNullable(personRepository.findByLogin(login))
                .flatMap(it -> it)
                .orElseThrow(() -> new ServiceException(Messages.UNREGISTERED_PERSON.getDescription()));
    }

    public void activatePerson(Long id) {
        Person person = this.findbyId(id);
        if (person.getStatus().equals(StatusPerson.ACTIVE)) {
            throw new ServiceException(Messages.PERSON_ALREADY_ACTIVE.getDescription());
        } else {
            person.setStatus(StatusPerson.ACTIVE);
            personRepository.save(person);
        }

    }

    public void inactivatePerson(Long id) {
        Person person = this.findbyId(id);
        if (person.getStatus().equals(StatusPerson.INACTIVE)) {
            throw new ServiceException(Messages.PERSON_ALREADY_INACTIVE.getDescription());
        } else {
            person.setStatus(StatusPerson.INACTIVE);
            personRepository.save(person);
        }

    }

    @Override
    public void verifyUniqueElement(Person person) {
        Optional<Person> personExists = personRepository.findByLogin(person.getLogin());

        personExists.ifPresent(person1 -> {
            throw new ServiceException(Messages.EXISTING_LOGIN.getDescription());
        });
    }

    @Override
    public void validateEnums(Person person) {
        if (person.getStatus() == null) {
            throw new ServiceException(Messages.INVALID_STATUS.getDescription());
        }
        if (person.getAccess() == null) {
            throw new ServiceException(Messages.INVALID_ACESS.getDescription());
        }
    }


}
