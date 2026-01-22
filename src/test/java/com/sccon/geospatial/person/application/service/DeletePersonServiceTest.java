package com.sccon.geospatial.person.application.service;

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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Delete Person Service Tests")
class DeletePersonServiceTest {

    @InjectMocks
    private DeletePersonService deletePersonService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should delete person successfully")
    void shouldDeletePersonSuccessfully() {
        var id = 1L;
        var person = PersonFixture.createPersonWithId(id);

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        deletePersonService.execute(id);

        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository).delete(person);
    }

    @Test
    @DisplayName("Should throw exception when person not found")
    void shouldThrowExceptionWhenPersonNotFound() {
        var id = 1L;

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> deletePersonService.execute(id))
            .isInstanceOf(PersonNotFoundException.class);
        verify(personRepository).findById(any(PersonId.class));
        verify(personRepository, never()).delete(any(Person.class));
    }

}
