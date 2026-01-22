package com.sccon.geospatial.person.adapter.out.persistence;

import java.time.LocalDate;

public class PersonEntity {

    private final Long id;
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate hireDate;

    public PersonEntity(Long id, String name, LocalDate birthDate, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
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

    public LocalDate getHireDate() {
        return hireDate;
    }

}
