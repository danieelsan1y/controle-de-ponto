package com.controledeponto.application.enums;

public enum AcessPerson {
    DENTIST(0),
    EMPLOYEE(1);

    private int code;
    private AcessPerson(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AcessPerson valueOf(int code) {
        for (AcessPerson value : AcessPerson.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Order Status code!");
    }
}
