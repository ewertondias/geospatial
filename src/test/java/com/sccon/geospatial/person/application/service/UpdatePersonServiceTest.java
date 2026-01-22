package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.UpdatePersonRequest;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
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
@DisplayName("Update Person Service Tests")
class UpdatePersonServiceTest {

    @InjectMocks
    private UpdatePersonService updatePersonService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should update person successfully")
    void shouldUpdatePersonSuccessfully() {

        var id = 1L;
        var existingPerson = PersonFactory.createPersonWithId(id);
        var request = new UpdatePersonRequest(
            "Jane Doe",
            LocalDate.of(1995, 5, 15),
            LocalDate.of(2021, 6, 20)
        );
        var updatedPerson = existingPerson.update(request.name(), request.birthDate(), request.hireDate());

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(existingPerson));
        when(personRepository.update(any(Person.class))).thenReturn(updatedPerson);

        var response = updatePersonService.execute(id, request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo("Jane Doe");
        assertThat(response.birthDate()).isEqualTo(request.birthDate());
        assertThat(response.hireDate()).isEqualTo(request.hireDate());
        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository).update(any(Person.class));
    }

    @Test
    @DisplayName("Should throw exception when person not found")
    void shouldThrowExceptionWhenPersonNotFound() {
        var id = 1L;
        var request = new UpdatePersonRequest(
            "Jane Doe",
            LocalDate.of(1995, 5, 15),
            LocalDate.of(2021, 6, 20)
        );

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> updatePersonService.execute(id, request))
            .isInstanceOf(PersonNotFoundException.class);

        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository, never()).update(any(Person.class));
    }

}
