package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonAgeOutputInvalidException;

public enum PersonAgeOutputEnum {

    DAYS,
    MONTHS,
    YEARS;

    public static PersonAgeOutputEnum fromString(String value) {
        for (PersonAgeOutputEnum outputEnum : PersonAgeOutputEnum.values()) {
            if (outputEnum.name().equalsIgnoreCase(value)) {
                return outputEnum;
            }
        }
x
        throw new PersonAgeOutputInvalidException(value);
    }

}
