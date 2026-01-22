package com.sccon.geospatial.person.factory;

import com.sccon.geospatial.person.domain.model.Person;

import java.time.LocalDate;

public class PersonFactory {

    public static Person createDefaultPerson() {
        return Person.create(
            1L,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );
    }

    public static Person createPersonWithId(Long id) {
        return Person.create(
            id,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );
    }

    public static Person createPersonWithDates(LocalDate birthDate, LocalDate hireDate) {
        return Person.create(
            1L,
            "John Doe",
            birthDate,
            hireDate
        );
    }

}
