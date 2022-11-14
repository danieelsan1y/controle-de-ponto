package com.controledeponto.application.enums;

public enum StatusPerson {
    ATIVO(0),
    INATIVO(1);

    private int code;
    private StatusPerson(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusPerson valueOf(int code) {
        for (StatusPerson value : StatusPerson.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Order Status code!");
    }
}
