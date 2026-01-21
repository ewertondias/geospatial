package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonIdInvalidException;

public record PersonId(Long value) {

    public static PersonId of(Long id) {
        try {
            return new PersonId(id);
        } catch (IllegalArgumentException e) {
            throw new PersonIdInvalidException();
        }
    }

}
