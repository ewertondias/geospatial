package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.application.CreatePersonUseCase;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonService implements CreatePersonUseCase {

    private final PersonRepository repository;

    public CreatePersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonResponse execute(CreatePersonRequest request) {
        return null;
    }

}
