package com.sccon.geospatial.person.adapter.in.api.doc;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonAgeResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonSalaryResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdateDetailPersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdatePersonRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Persons", description = "Managing persons")
public interface PersonApiDoc {

    @Operation(summary = "Get all persons",
        description = "Retrieves a list of all persons"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of persons retrieved successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
        )),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<List<PersonResponse>> getAll();

    @Operation(summary = "Create a new person",
        description = "Creates a new person with the provided information"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Person created successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
        )),
        @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "409", description = "Person with the same ID already exists", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonResponse> create(CreatePersonRequest request);

    @Operation(summary = "Delete a person",
        description = "Deletes a person by ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Person deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Void> delete(@Parameter(description = "Person id", required = true) Long id);

    @Operation(summary = "Update a person",
        description = "Updates an existing person with the provided information"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person updated successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
        )),
        @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonResponse> update(@Parameter(description = "Person id", required = true) Long id,
                                          UpdatePersonRequest request);

    @Operation(summary = "Update person details",
        description = "Updates specific details of an existing person"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person details updated successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
        )),
        @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonResponse> updateDetail(@Parameter(description = "Person id", required = true) Long id,
                                                UpdateDetailPersonRequest request);

    @Operation(summary = "Get person by ID",
        description = "Retrieves a person by their unique identifier"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person retrieved successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
        )),
        @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonResponse> getById(@Parameter(description = "Person id", required = true) Long id);

    @Operation(summary = "Get person age",
        description = "Retrieves the age of a person by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person age retrieved successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonAgeResponse.class)
        )),
        @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonAgeResponse> getAge();

    @Operation(summary = "Get person salary",
        description = "Retrieves the salary of a person by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Person salary retrieved successfully", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonSalaryResponse.class)
        )),
        @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Person not found", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<PersonSalaryResponse> getSalary();

}
