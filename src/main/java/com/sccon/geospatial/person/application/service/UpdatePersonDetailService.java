package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdateDetailPersonRequest;
import com.sccon.geospatial.person.application.UpdatePersonDetailUseCase;
import com.sccon.geospatial.person.application.assembler.PersonAssembler;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonDetailService implements UpdatePersonDetailUseCase {

    private final PersonRepository repository;

    public UpdatePersonDetailService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonResponse execute(Long id, UpdateDetailPersonRequest request) {
        var personId = PersonId.of(id);
        var person = repository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(personId.value().toString()));

        var personToUpdate = person.updateDetail(request.name(), request.birthDate(), request.admissionDate());

        var updatedPerson = repository.update(personToUpdate);

        return PersonAssembler.toResponse(updatedPerson);
    }

}
