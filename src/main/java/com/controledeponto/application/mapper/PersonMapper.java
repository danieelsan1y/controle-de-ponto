package com.controledeponto.application.mapper;

import com.controledeponto.application.dto.PersonDTO;
import com.controledeponto.application.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    private static  final ModelMapper MODEL_MAPPER = new ModelMapper();

    public PersonDTO toPersonDTO(Person person) {
        return MODEL_MAPPER.map(person, PersonDTO.class);
    }

    public Person personDTOToPerson(PersonDTO personDTO) {
        return  MODEL_MAPPER.map(personDTO, Person.class);
    }

    public List<PersonDTO> toPersonDTOList(List<Person> persons) {
        List<PersonDTO> personDTOS = persons.stream().map(person -> toPersonDTO(person)).collect(Collectors.toList());
        return personDTOS;
    }
}
