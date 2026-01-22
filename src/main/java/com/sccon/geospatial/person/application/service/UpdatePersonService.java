package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdatePersonRequest;
import com.sccon.geospatial.person.application.UpdatePersonUseCase;
import com.sccon.geospatial.person.application.assembler.PersonAssembler;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonService implements UpdatePersonUseCase {

    private final PersonRepository repository;

    public UpdatePersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonResponse execute(Long id, UpdatePersonRequest request) {
        var personId = PersonId.of(id);
        var person = repository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(personId.value().toString()));

        var personToUpdate = person.update(request.name(), request.birthDate(), request.hireDate());

        var updatedPerson = repository.update(personToUpdate);

        return PersonAssembler.toResponse(updatedPerson);
    }

}
