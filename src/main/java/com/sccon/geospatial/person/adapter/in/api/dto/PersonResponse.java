package com.sccon.geospatial.person.adapter.in.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record PersonResponse(
    @Schema(description = "Person id", examples = "4")
    Long id,

    @Schema(description = "Person name", examples = "John Doe")
    String name,

    @Schema(description = "Person birthdate", examples = "1989-05-15")
    LocalDate birthDate,

    @Schema(description = "Person hire date", examples = "2010-01-01")
    LocalDate hireDate) {
}
