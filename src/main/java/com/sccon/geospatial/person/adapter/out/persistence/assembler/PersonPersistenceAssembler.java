package com.sccon.geospatial.person.adapter.out.persistence.assembler;

import com.sccon.geospatial.person.adapter.out.persistence.PersonEntity;
import com.sccon.geospatial.person.domain.model.Person;

public final class PersonPersistenceAssembler {

    private PersonPersistenceAssembler() {
    }

    public static PersonEntity toEntity(Long id, Person person) {
        return new PersonEntity(
            id,
            person.name(),
            person.birthDate().value(),
            person.admissionDate().value()
        );
    }

    public static Person toDomain(PersonEntity entity) {
        return Person.create(
            entity.getId(),
            entity.getName(),
            entity.getBirthDate(),
            entity.getAdmissionDate()
        );
    }

}
