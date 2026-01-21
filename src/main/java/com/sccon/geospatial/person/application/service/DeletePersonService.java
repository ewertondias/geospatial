package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.application.DeletePersonUseCase;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonService implements DeletePersonUseCase {

    private final PersonRepository repository;

    public DeletePersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) {
        var personId = PersonId.of(id);
        var person = repository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(personId.value().toString()));

        repository.delete(person);
    }

}
