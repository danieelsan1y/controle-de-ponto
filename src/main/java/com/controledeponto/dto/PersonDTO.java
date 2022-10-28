package com.controledeponto.dto;

import com.controledeponto.anonation.Empty;
import com.controledeponto.enums.AcessPerson;
import com.controledeponto.enums.StatusPerson;
import com.controledeponto.validations.Validation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO extends Validation {

    private Long id;
    @Empty
    public String login;


    private String name;

    private String lastname;

    private String password;

    private String acess;

    private String status;
}
