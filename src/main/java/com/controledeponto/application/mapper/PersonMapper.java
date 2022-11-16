package com.controledeponto.application.mapper;

import com.controledeponto.application.dto.PersonDTO;
import com.controledeponto.application.dto.PersonFindDTO;
import com.controledeponto.application.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper{
    private static  final ModelMapper MODEL_MAPPER = new ModelMapper();

    public PersonDTO toPersonInsertDTO(Person person) {
        return MODEL_MAPPER.map(person, PersonDTO.class);
    }

    public PersonFindDTO toPersonFindDTO(Person person) {
        return MODEL_MAPPER.map(person, PersonFindDTO.class);
    }
    public Person personInsertDTOToPerson(PersonDTO personDTO) {
        return  MODEL_MAPPER.map(personDTO, Person.class);
    }

    public List<PersonFindDTO> toPersonDTOList(List<Person> persons) {
        List<PersonFindDTO> personFindDTOS = persons.stream().map(this::toPersonFindDTO).collect(Collectors.toList());
        return personFindDTOS;
    }

}
