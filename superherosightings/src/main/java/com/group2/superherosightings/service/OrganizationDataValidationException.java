package com.group2.superherosightings.service;

public class OrganizationDataValidationException extends Exception{

  public OrganizationDataValidationException(String message) {
    super(message);
  }

  public OrganizationDataValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
