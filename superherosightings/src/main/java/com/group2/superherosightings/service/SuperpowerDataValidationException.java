package com.group2.superherosightings.service;

public class SuperpowerDataValidationException extends Exception {

    public SuperpowerDataValidationException(String message) {
        super(message);
    }

    public SuperpowerDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
