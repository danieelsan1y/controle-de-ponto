package com.controledeponto.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonFindDTO {

    private Long id;

    public String login;

    private String name;

    private String lastname;

    private String access;

    private String status;
}
