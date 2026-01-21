package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdateDetailPersonRequest;

public interface UpdatePersonDetailUseCase {

    PersonResponse execute(Long id, UpdateDetailPersonRequest request);

}
