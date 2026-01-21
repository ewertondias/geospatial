package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonBirthDateInvalidException;

import java.time.LocalDate;

public record PersonBirthDate(LocalDate value) {

    public PersonBirthDate {
        if (value == null) {
            throw new PersonBirthDateInvalidException();
        }
    }

    public static PersonBirthDate of(LocalDate value) {
        return new PersonBirthDate(value);
    }

}
