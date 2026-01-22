package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonSalaryResponse;

public interface GetPersonSalaryByIdUseCase {

    PersonSalaryResponse execute(Long id, String output);

}
