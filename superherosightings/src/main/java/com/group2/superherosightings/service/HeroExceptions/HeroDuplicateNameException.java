package com.group2.superherosightings.service.HeroExceptions;

/**
 * @author raniaouassif on 2023-07-20
 */
public class HeroDuplicateNameException extends Exception{
    public HeroDuplicateNameException(String message) {
        super(message);
    }
    public HeroDuplicateNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
