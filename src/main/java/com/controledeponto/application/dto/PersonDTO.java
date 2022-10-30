package com.controledeponto.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private Long id;
    public String login;


    private String name;

    private String lastname;

    private String password;

    private String acess;

    private String status;
}
