package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.application.GetPersonByIdUseCase;
import com.sccon.geospatial.person.application.assembler.PersonAssembler;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPersonByIdService implements GetPersonByIdUseCase {

    private final PersonRepository repository;

    public GetPersonByIdService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonResponse execute(Long id) {
        var personId = PersonId.of(id);
        var person = repository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(personId.value().toString()));

        return PersonAssembler.toResponse(person);
    }

}
