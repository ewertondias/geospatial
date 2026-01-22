package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.model.PersonId;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import com.sccon.geospatial.person.factory.PersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get Person By Id Service Tests")
class GetPersonByIdServiceTest {

    @InjectMocks
    private GetPersonByIdService getPersonByIdService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should return person when found")
    void shouldReturnPersonWhenFound() {
        var id = 1L;
        var person = PersonFactory.createPersonWithId(id);

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        var response = getPersonByIdService.execute(id);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        assertThat(response.name()).isEqualTo(person.name());
        verify(personRepository).findById(any(PersonId.class));
    }

    @Test
    @DisplayName("Should throw exception when person not found")
    void shouldThrowExceptionWhenPersonNotFound() {
        var id = 1L;

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getPersonByIdService.execute(id))
            .isInstanceOf(PersonNotFoundException.class);

        verify(personRepository).findById(any(PersonId.class));
    }

}
