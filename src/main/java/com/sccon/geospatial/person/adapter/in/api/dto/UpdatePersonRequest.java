package com.sccon.geospatial.person.adapter.in.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdatePersonRequest(
    @Schema(description = "Person name", examples = "John Doe")
    @NotBlank(message = "{person.name.null}")
    @Size(min = 3, max = 150, message = "{student.name.size}")
    String name,

    @Schema(description = "Person birthdate", examples = "1989-05-15")
    @NotNull(message = "{person.birthDate.null}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate,

    @Schema(description = "Person admission date", examples = "2010-01-01")
    @NotNull(message = "{person.admissionDate.null}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate admissionDate) {
}
