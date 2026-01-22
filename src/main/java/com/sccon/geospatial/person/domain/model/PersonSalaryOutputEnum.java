package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonSalaryOutputInvalidException;

public enum PersonSalaryOutputEnum {

    MIN,
    FULL;

    public static PersonSalaryOutputEnum fromString(String value) {
        for (PersonSalaryOutputEnum output : PersonSalaryOutputEnum.values()) {
            if (output.name().equalsIgnoreCase(value)) {
                return output;
            }
        }

        throw new PersonSalaryOutputInvalidException(value);
    }

}
