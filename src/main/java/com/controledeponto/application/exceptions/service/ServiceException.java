package com.controledeponto.application.exceptions.service;

public class ServiceException extends RuntimeException{

    public ServiceException (String message) {
        super(message);
    }
}