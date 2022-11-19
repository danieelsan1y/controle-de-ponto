package com.controledeponto.application.enums;

public enum DayOfWeek {

    SUNDAY(0),

    MONDAY(1),

    TUESDAY(2),

    WEDNESDAY(3),

    THURSDAY(4),

    FRIDAY(5),

    SATURDAY(6);


    private int code;

    private DayOfWeek(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DayOfWeek valueOf(int code) {
        for (DayOfWeek value : DayOfWeek.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Order Status code!");
    }

    public static Integer returnCode(String text) {
        for (DayOfWeek value : DayOfWeek.values()) {
            if (value.name().equals(text)) {
                return value.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid Order Status code!");
    }

    public static String dayForWeekBrazil(String text) {
            if (SUNDAY.getCode() == returnCode(text)) {
                return "Domingo";
            }
            if (MONDAY.getCode() == returnCode(text)) {
                return "Segunda-Feira";
            }
            if (TUESDAY.getCode() == returnCode(text)) {
                return "Terça-Feira";
            }
            if (WEDNESDAY.getCode() == returnCode(text)) {
                return "Quarta-Feira";
            }
            if (THURSDAY.getCode() == returnCode(text)) {
                return "Quinta-Feira";
            }
            if (FRIDAY.getCode() == returnCode(text)) {
                return "Sexta-Feira";
            }
            if (SATURDAY.getCode() == returnCode(text)) {
                return "Sábado";
            }

        throw new IllegalArgumentException("Invalid Order Status text!");
    }

}
