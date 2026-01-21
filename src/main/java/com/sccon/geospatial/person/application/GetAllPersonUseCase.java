package com.sccon.geospatial.person.application;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;

import java.util.List;

public interface GetAllPersonUseCase {

    List<PersonResponse> execute();

}
