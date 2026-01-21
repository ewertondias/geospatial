package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;

public interface GetPersonByIdUseCase {

    PersonResponse execute(Long id);

}
