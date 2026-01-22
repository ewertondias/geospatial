package com.sccon.geospatial.person.application.assembler;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.domain.model.Person;

public final class PersonAssembler {

    private PersonAssembler() {
    }

    public static Person toDomain(CreatePersonRequest request) {
        return Person.create(
            request.id(),
            request.name(),
            request.birthDate(),
            request.hireDate()
        );
    }

    public static PersonResponse toResponse(Person person) {
        return new PersonResponse(
            person.id().value(),
            person.name(),
            person.birthDate().value(),
            person.hireDate().value()
        );
    }

}
