package com.controledeponto.application.message;

public enum Messages {

    UNEXPECTED_ERROR("Ocorreu um erro inesperado."),
    REQUIRED_FIELDS("Campo(s) obrigatórios em branco!");


    private String descricao;
    Messages(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}
