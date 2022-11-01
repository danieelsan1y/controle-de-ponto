package com.controledeponto.application.test;

import com.controledeponto.application.enums.AcessPerson;
import com.controledeponto.application.enums.StatusPerson;
import com.controledeponto.application.model.Person;
import com.controledeponto.application.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration


public class TestConfig implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;


    @Override
    public void run(String... args) throws Exception {

        personRepository.save(new Person(null,"danieelsan1","Daniel","Santana","1234", AcessPerson.DENTIST, StatusPerson.ACTIVE));

    }

}