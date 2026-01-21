package com.sccon.geospatial.person.adapter.in.api;

import com.sccon.geospatial.person.adapter.in.api.doc.PersonApiDoc;
import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonAgeResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonSalaryResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdateDetailPersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdatePersonRequest;
import com.sccon.geospatial.person.application.CreatePersonUseCase;
import com.sccon.geospatial.person.application.DeletePersonUseCase;
import com.sccon.geospatial.person.application.GetAllPersonUseCase;
import com.sccon.geospatial.person.application.GetPersonByIdUseCase;
import com.sccon.geospatial.person.application.UpdatePersonDetailUseCase;
import com.sccon.geospatial.person.application.UpdatePersonUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final DeletePersonUseCase deletePerson;
    private final UpdatePersonUseCase updatePerson;
    private final UpdatePersonDetailUseCase updatePersonDetail;
    private final GetPersonByIdUseCase getPersonById;

    public PersonController(GetAllPersonUseCase getAllPerson, CreatePersonUseCase createPerson, DeletePersonUseCase deletePerson, UpdatePersonUseCase updatePerson, UpdatePersonDetailUseCase updatePersonDetail, GetPersonByIdUseCase getPersonById) {
        this.getAllPerson = getAllPerson;
        this.createPerson = createPerson;
        this.deletePerson = deletePerson;
        this.updatePerson = updatePerson;
        this.updatePersonDetail = updatePersonDetail;
        this.getPersonById = getPersonById;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deletePerson.execute(id);

        return ResponseEntity.noContent()
            .build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody @Valid UpdatePersonRequest request) {
        var person = updatePerson.execute(id, request);

        return ResponseEntity.ok(person);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<PersonResponse> updateDetail(@PathVariable Long id, @RequestBody @Valid UpdateDetailPersonRequest request) {
        var person = updatePersonDetail.execute(id, request);

        return ResponseEntity.ok(person);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable Long id) {
        var person = getPersonById.execute(id);

        return ResponseEntity.ok(person);
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
