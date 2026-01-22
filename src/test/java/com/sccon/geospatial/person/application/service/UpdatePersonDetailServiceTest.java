package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdateDetailPersonRequest;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.Person;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import com.sccon.geospatial.person.fixture.PersonFixture;
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
@DisplayName("Update Person Detail Service Tests")
class UpdatePersonDetailServiceTest {

    @InjectMocks
    private UpdatePersonDetailService updatePersonDetailService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should update person detail with partial data")
    void shouldUpdatePersonDetailWithPartialData() {
        var id = 1L;
        var existingPerson = PersonFixture.createPersonWithId(id);
        var request = new UpdateDetailPersonRequest(
            "Jane Doe",
            null,
            null
        );

        var updatedPerson = existingPerson.updateDetail(request.name(), request.birthDate(), request.hireDate());

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(existingPerson));
        when(personRepository.update(any(Person.class))).thenReturn(updatedPerson);

        var response = updatePersonDetailService.execute(id, request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo("Jane Doe");
        assertThat(response.birthDate()).isEqualTo(existingPerson.birthDate().value());
        assertThat(response.hireDate()).isEqualTo(existingPerson.hireDate().value());

        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository).update(any(Person.class));
    }

    @Test
    @DisplayName("Should update person detail keeping original name when name is null")
    void shouldUpdatePersonDetailKeepingOriginalNameWhenNameIsNull() {
        var id = 1L;
        var existingPerson = PersonFixture.createPersonWithId(id);
        var newBirthDate = LocalDate.of(1995, 5, 15);
        var request = new UpdateDetailPersonRequest(
            null,
            newBirthDate,
            null
        );

        var updatedPerson = existingPerson.updateDetail(request.name(), request.birthDate(), request.hireDate());

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(existingPerson));
        when(personRepository.update(any(Person.class))).thenReturn(updatedPerson);

        var response = updatePersonDetailService.execute(id, request);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo(existingPerson.name());
        assertThat(response.birthDate()).isEqualTo(newBirthDate);
        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository).update(any(Person.class));
    }

    @Test
    @DisplayName("Should throw exception when person not found")
    void shouldThrowExceptionWhenPersonNotFound() {
        var id = 1L;
        var request = new UpdateDetailPersonRequest(
            "Jane Doe",
            null,
            null
        );

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> updatePersonDetailService.execute(id, request))
            .isInstanceOf(PersonNotFoundException.class);

        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository, never()).update(any(Person.class));
    }

}
