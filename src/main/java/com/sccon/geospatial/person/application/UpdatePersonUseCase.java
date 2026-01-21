package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdatePersonRequest;

public interface UpdatePersonUseCase {

    PersonResponse execute(Long id, UpdatePersonRequest request);

}
