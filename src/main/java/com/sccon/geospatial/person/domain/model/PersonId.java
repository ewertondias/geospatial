package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonIdInvalidException;

public record PersonId(Long value) {

    public PersonId {
        if (value == null) {
            throw new PersonIdInvalidException();
        }
    }

}
