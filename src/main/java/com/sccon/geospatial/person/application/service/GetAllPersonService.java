package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.application.GetAllPersonUseCase;
import com.sccon.geospatial.person.application.assembler.PersonAssembler;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetAllPersonService implements GetAllPersonUseCase {

    private final PersonRepository repository;

    public GetAllPersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonResponse> execute() {
        var persons = repository.findAll();

        return persons.stream()
            .map(PersonAssembler::toResponse)
            .sorted(Comparator.comparing(PersonResponse::name, String.CASE_INSENSITIVE_ORDER))
            .toList();
    }

}
