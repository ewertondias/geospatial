package com.sccon.geospatial.person.adapter.in.api;

import com.sccon.geospatial.person.adapter.in.api.doc.PersonApiDoc;
import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonAgeResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonSalaryResponse;
import com.sccon.geospatial.person.application.CreatePersonUseCase;
import com.sccon.geospatial.person.application.GetAllPersonUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController implements PersonApiDoc {

    private final GetAllPersonUseCase getAllPerson;
    private final CreatePersonUseCase createPerson;

    public PersonController(GetAllPersonUseCase getAllPerson, CreatePersonUseCase createPerson) {
        this.getAllPerson = getAllPerson;
        this.createPerson = createPerson;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll() {
        var persons = getAllPerson.execute();

        return ResponseEntity.ok(persons);
    }

    @Override
    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody @Valid CreatePersonRequest request) {
        var person = createPerson.execute(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(person.id())
            .toUri();

        return ResponseEntity.created(uri)
            .body(person);
    }

    @Override
    public ResponseEntity<Void> delete() {
        return null;
    }

    @Override
    public ResponseEntity<PersonResponse> update() {
        return null;
    }

    @Override
    public ResponseEntity<PersonResponse> updateDetail() {
        return null;
    }

    @Override
    public ResponseEntity<PersonResponse> getById() {
        return null;
    }

    @Override
    public ResponseEntity<PersonAgeResponse> getAge() {
        return null;
    }

    @Override
    public ResponseEntity<PersonSalaryResponse> getSalary() {
        return null;
    }

}
