package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonAgeResponse;

public interface GetPersonAgeByIdUseCase {

    PersonAgeResponse execute(Long id, String output);

}
