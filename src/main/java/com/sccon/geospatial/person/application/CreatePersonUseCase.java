package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;

public interface CreatePersonUseCase {

    PersonResponse execute(CreatePersonRequest request);

}
