package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get Person Age By Id Service Tests")
class GetPersonAgeByIdServiceTest {

    @InjectMocks
    private GetPersonAgeByIdService getPersonAgeByIdService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should return age in years")
    void shouldReturnAgeInYears() {
        var id = 1L;
        var birthDate = LocalDate.now().minusYears(30);
        var person = PersonFixture.createPersonWithDates(birthDate, LocalDate.now().minusYears(5));

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        var response = getPersonAgeByIdService.execute(id, "years");

        assertThat(response).isNotNull();
        assertThat(response.age()).isEqualTo(30L);
        verify(personRepository).findById(any(PersonId.class));
    }

    @Test
    @DisplayName("Should return age in months")
    void shouldReturnAgeInMonths() {
        var id = 1L;
        var birthDate = LocalDate.now().minusMonths(12);
        var person = PersonFixture.createPersonWithDates(birthDate, LocalDate.now().minusMonths(6));

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        var response = getPersonAgeByIdService.execute(id, "months");

        assertThat(response).isNotNull();
        assertThat(response.age()).isEqualTo(12L);
        verify(personRepository).findById(any(PersonId.class));
    }

    @Test
    @DisplayName("Should return age in days")
    void shouldReturnAgeInDays() {
        var id = 1L;
        var birthDate = LocalDate.now().minusDays(365);
        var person = PersonFixture.createPersonWithDates(birthDate, LocalDate.now().minusDays(180));

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        var response = getPersonAgeByIdService.execute(id, "days");

        assertThat(response).isNotNull();
        assertThat(response.age()).isEqualTo(365L);
        verify(personRepository).findById(any(PersonId.class));
    }

    @Test
    @DisplayName("Should throw exception when person not found")
    void shouldThrowExceptionWhenPersonNotFound() {
        var id = 1L;

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getPersonAgeByIdService.execute(id, "years"))
            .isInstanceOf(PersonNotFoundException.class);

        verify(personRepository).findById(any(PersonId.class));
    }

}
