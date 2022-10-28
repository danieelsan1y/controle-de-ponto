package com.controledeponto.mapper;

import com.controledeponto.dto.PersonDTO;
import com.controledeponto.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    private static  final ModelMapper MODEL_MAPPER = new ModelMapper();

    public PersonDTO toPersonDTO(Person person) {
        return MODEL_MAPPER.map(person, PersonDTO.class);
    }

    public Person personDTOToPerson(PersonDTO personDTO) {
        return  MODEL_MAPPER.map(personDTO, Person.class);
    }
}
