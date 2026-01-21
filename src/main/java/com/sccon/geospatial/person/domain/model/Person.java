package com.sccon.geospatial.person.domain.model;

import java.time.LocalDate;

public record Person(PersonId id, String name, PersonBirthDate birthDate, PersonAdmissionDate admissionDate) {

    public static Person create(Long id, String name, LocalDate birthDate, LocalDate admissionDate) {
        var personId = PersonId.of(id);
        var personBirthDate = PersonBirthDate.of(birthDate);
        var personAdmissionDate = PersonAdmissionDate.of(admissionDate);

        return new Person(
            personId,
            name,
            personBirthDate,
            personAdmissionDate
        );
    }

}
