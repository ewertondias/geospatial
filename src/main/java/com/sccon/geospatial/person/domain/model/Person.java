package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonHireDateInvalidException;

import java.time.LocalDate;

public record Person(PersonId id, String name, PersonBirthDate birthDate, PersonHireDate hireDate) {

    public Person {
        if (hireDate.value().isBefore(birthDate.value())) {
            throw new PersonHireDateInvalidException();
        }
    }

    public static Person create(Long id, String name, LocalDate birthDate, LocalDate hireDate) {
        var personId = PersonId.of(id);
        var personBirthDate = PersonBirthDate.of(birthDate);
        var personHireDate = PersonHireDate.of(hireDate);

        return new Person(
            personId,
            name,
            personBirthDate,
            personHireDate
        );
    }

    public Person update(String name, LocalDate newBirthDate, LocalDate newHireDate) {
        var personBirthDate = PersonBirthDate.of(newBirthDate);
        var personHireDate = PersonHireDate.of(newHireDate);

        return new Person(
            this.id,
            name,
            personBirthDate,
            personHireDate
        );
    }

    public Person updateDetail(String name, LocalDate newBirthDate, LocalDate newHireDate) {
        return new Person(
            this.id,
            name != null ? name : this.name,
            newBirthDate != null ? PersonBirthDate.of(newBirthDate) : this.birthDate,
            newHireDate != null ? PersonHireDate.of(newHireDate) : this.hireDate
        );
    }

}
