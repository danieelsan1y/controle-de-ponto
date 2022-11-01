package com.controledeponto.application.message;

public enum Messages {

    UNEXPECTED_ERROR("Ocorreu um erro inesperado."),
    REQUIRED_FIELDS("Campo(s) obrigatórios em branco!"),
    EXISTING_LOGIN ("Login já cadastrado, por favor informar outro!"),

    UNREGISTERED_PERSON ("Pessoa não cadastrada!"),

    INVALID_STATUS ("Status informado é inválido."),

    INVALID_ACESS ("Acesso informado é inválido."),

    PERSON_ALREADY_ACTIVE("Pessoa já está ativa."),

    PERSON_ALREADY_INACTIVE ("Pessoa já está inativa.");
    private String description;
    Messages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
