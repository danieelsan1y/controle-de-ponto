package com.controledeponto.application.controller;

import com.controledeponto.application.dto.PersonFindDTO;
import com.controledeponto.application.model.Person;
import com.controledeponto.application.dto.PersonInsertDTO;
import com.controledeponto.application.mapper.PersonMapper;
import com.controledeponto.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<PersonFindDTO> insert(@RequestBody PersonInsertDTO personInsertDTO) {
        Person person = personMapper.personInsertDTOToPerson(personInsertDTO);
        personService.insert(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personMapper.toPersonFindDTO(person));
    }

    @GetMapping
    public ResponseEntity<List<PersonFindDTO>> findAll() {
        return ResponseEntity.ok()
                .body(personMapper.toPersonDTOList(personService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonFindDTO> findById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(personMapper
                        .toPersonFindDTO(personService.findbyId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PersonInsertDTO personInsertDTO) {
        personService.update(id, personMapper.personInsertDTOToPerson(personInsertDTO));
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<PersonFindDTO> findByLogin(@PathVariable String login) {
        return ResponseEntity
                .ok()
                .body(personMapper.toPersonFindDTO(personService.findByLogin(login)));
    }

    @PutMapping("/status/inactivate/{id}")
    public ResponseEntity<Void> inactivatePerson(@PathVariable Long id) {
        personService.inactivatePerson(id);
        return ResponseEntity
                .ok()
                .build();
    }
    @PutMapping("/status/activate/{id}")
    public ResponseEntity<Void> activatedPerson(@PathVariable Long id) {
        personService.activatePerson(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
