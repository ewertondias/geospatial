package com.sccon.geospatial.person.adapter.out.persistence;

import java.time.LocalDate;

public class PersonEntity {

    private final Long id;
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate admissionDate;

    public PersonEntity(Long id, String name, LocalDate birthDate, LocalDate admissionDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

}
