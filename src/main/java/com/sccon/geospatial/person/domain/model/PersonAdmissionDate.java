package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonAdmissionDateInvalidException;

import java.time.LocalDate;

public record PersonAdmissionDate(LocalDate value) {

    public PersonAdmissionDate {
        if (value == null) {
            throw new PersonAdmissionDateInvalidException();
        }
    }

    public static PersonAdmissionDate of(LocalDate value) {
        return new PersonAdmissionDate(value);
    }

}
