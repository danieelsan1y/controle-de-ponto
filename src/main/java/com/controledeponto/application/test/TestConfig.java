package com.controledeponto.application.test;

import com.controledeponto.application.enums.AcessPerson;
import com.controledeponto.application.enums.StatusPerson;
import com.controledeponto.application.model.Person;
import com.controledeponto.application.model.Record;
import com.controledeponto.application.repositories.PersonRepository;
import com.controledeponto.application.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration


public class TestConfig implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RecordRepository recordRepository;


    @Override
    public void run(String... args) throws Exception {



    }

}
