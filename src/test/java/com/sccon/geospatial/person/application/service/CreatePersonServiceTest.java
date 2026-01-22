package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.domain.exception.PersonAlreadyExistsException;
import com.sccon.geospatial.person.domain.model.Person;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import com.sccon.geospatial.person.factory.PersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Create Person Service Tests")
class CreatePersonServiceTest {

    @InjectMocks
    private CreatePersonService createPersonService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should create person successfully when id is null")
    void shouldCreatePersonSuccessfullyWhenIdIsNull() {
        var request = new CreatePersonRequest(
            null,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        var savedPerson = PersonFactory.createDefaultPerson();

        when(personRepository.save(any(Person.class))).thenReturn(savedPerson);

        var response = createPersonService.execute(request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(savedPerson.id().value());
        assertThat(response.name()).isEqualTo(savedPerson.name());

        verify(personRepository, never()).findById(any());
        verify(personRepository).save(any(Person.class));
    }

    @Test
    @DisplayName("Should create person successfully when id is provided and person does not exist")
    void shouldCreatePersonSuccessfullyWhenIdIsProvidedAndPersonDoesNotExist() {
        var request = new CreatePersonRequest(
            1L,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        var savedPerson = PersonFactory.createPersonWithId(1L);

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());
        when(personRepository.save(any(Person.class))).thenReturn(savedPerson);

        var response = createPersonService.execute(request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(1L);

        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository).save(any(Person.class));
    }

    @Test
    @DisplayName("Should throw exception when person with id already exists")
    void shouldThrowExceptionWhenPersonWithIdAlreadyExists() {
        var request = new CreatePersonRequest(
            1L,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        var existingPerson = PersonFactory.createPersonWithId(1L);

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(existingPerson));

        assertThatThrownBy(() -> createPersonService.execute(request))
            .isInstanceOf(PersonAlreadyExistsException.class);

        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository, never()).save(any(Person.class));
    }

}
