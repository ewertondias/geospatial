package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonAgeResponse;
import com.sccon.geospatial.person.application.GetPersonAgeByIdUseCase;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.PersonAgeOutputEnum;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class GetPersonAgeByIdService implements GetPersonAgeByIdUseCase {

    private final Map<PersonAgeOutputEnum, ChronoUnit> outputUnit = Map.of(
        PersonAgeOutputEnum.DAYS, ChronoUnit.DAYS,
        PersonAgeOutputEnum.MONTHS, ChronoUnit.MONTHS,
        PersonAgeOutputEnum.YEARS, ChronoUnit.YEARS
    );

    private final PersonRepository repository;

    public GetPersonAgeByIdService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public PersonAgeResponse execute(Long id, String output) {
        var personId = PersonId.of(id);
        var person = repository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(personId.value().toString()));

        var today = LocalDate.now();
        var birthDate = person.birthDate().value();

        var ageOutputEnum = PersonAgeOutputEnum.fromString(output);
        var unit = outputUnit.get(ageOutputEnum);

        var value = unit.between(birthDate, today);

        return new PersonAgeResponse(value);
    }

}
