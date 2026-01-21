package com.sccon.geospatial.person.domain.exception;

public class PersonAlreadyExistsException extends RuntimeException {

    public PersonAlreadyExistsException(String message) {
        super(message);
    }

}
