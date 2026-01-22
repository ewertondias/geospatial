package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonHireDateInvalidException;

import java.time.LocalDate;

public record PersonHireDate(LocalDate value) {

    public PersonHireDate {
        if (value == null) {
            throw new PersonHireDateInvalidException();
        }

        if (value.isAfter(LocalDate.now())) {
            throw new PersonHireDateInvalidException();
        }
    }

    public static PersonHireDate of(LocalDate value) {
        return new PersonHireDate(value);
    }

}
