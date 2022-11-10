package com.controledeponto.application.mapper;

import com.controledeponto.application.dto.PersonInsertDTO;
import com.controledeponto.application.dto.PersonFindDTO;
import com.controledeponto.application.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper{
    private static  final ModelMapper MODEL_MAPPER = new ModelMapper();

    public PersonInsertDTO toPersonInsertDTO(Person person) {
        return MODEL_MAPPER.map(person, PersonInsertDTO.class);
    }

    public PersonFindDTO toPersonFindDTO(Person person) {
        return MODEL_MAPPER.map(person, PersonFindDTO.class);
    }
    public Person personInsertDTOToPerson(PersonInsertDTO personInsertDTO) {
        return  MODEL_MAPPER.map(personInsertDTO, Person.class);
    }

    public List<PersonFindDTO> toPersonDTOList(List<Person> persons) {
        List<PersonFindDTO> personFindDTOS = persons.stream().map(person -> toPersonFindDTO(person)).collect(Collectors.toList());
        return personFindDTOS;
    }

}
