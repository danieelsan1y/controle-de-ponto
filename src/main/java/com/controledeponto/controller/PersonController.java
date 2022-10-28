package com.controledeponto.controller;

import com.controledeponto.dto.PersonDTO;
import com.controledeponto.mapper.PersonMapper;
import com.controledeponto.model.Person;
import com.controledeponto.service.PersonService;
import com.controledeponto.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<PersonDTO> insert(@RequestBody PersonDTO personDTO) {
        Validation.verifyNullFiled(personDTO);
        Person person = personMapper.personDTOToPerson(personDTO);
        personService.insert(person);
        personDTO = personMapper.toPersonDTO(person);
       return  ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
    }

    @GetMapping
    public ResponseEntity<PersonDTO> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(personMapper.toPersonDTO(personService.findbyId(id)));
    }



}
