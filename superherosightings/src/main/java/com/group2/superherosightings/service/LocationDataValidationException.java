package com.group2.superherosightings.service;

public class LocationDataValidationException extends Exception {

    public LocationDataValidationException(String message) {
        super(message);
    }

    public LocationDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
