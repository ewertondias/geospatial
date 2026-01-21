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

    public Person update(String name, LocalDate newBirthDate, LocalDate newAdmissionDate) {
        var personBirthDate = PersonBirthDate.of(newBirthDate);
        var personAdmissionDate = PersonAdmissionDate.of(newAdmissionDate);

        return new Person(
            this.id,
            name,
            personBirthDate,
            personAdmissionDate
        );
    }

    public Person updateDetail(String name, LocalDate newBirthDate, LocalDate newAdmissionDate) {
        return new Person(
            this.id,
            name != null ? name : this.name,
            newBirthDate != null ? PersonBirthDate.of(newBirthDate) : this.birthDate,
            newAdmissionDate != null ? PersonAdmissionDate.of(newAdmissionDate) : this.admissionDate
        );
    }

}
