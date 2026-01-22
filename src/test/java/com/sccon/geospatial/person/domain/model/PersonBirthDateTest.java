package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonBirthDateInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayName("Person Birth Date Domain Model Tests")
class PersonBirthDateTest {

    @Test
    @DisplayName("Should create birth date successfully with valid date")
    void shouldCreateBirthDateSuccessfully() {
        var birthDate = LocalDate.of(1990, 1, 1);

        var personBirthDate = PersonBirthDate.of(birthDate);

        assertThat(personBirthDate).isNotNull();
        assertThat(personBirthDate.value()).isEqualTo(birthDate);
    }

    @Test
    @DisplayName("Should throw exception when birth date is null")
    void shouldThrowExceptionWhenBirthDateIsNull() {
        assertThatThrownBy(() -> PersonBirthDate.of(null))
            .isInstanceOf(PersonBirthDateInvalidException.class);
    }

    @Test
    @DisplayName("Should throw exception when birth date is in the future")
    void shouldThrowExceptionWhenBirthDateIsInTheFuture() {
        var futureDate = LocalDate.now().plusDays(1);

        assertThatThrownBy(() -> PersonBirthDate.of(futureDate))
            .isInstanceOf(PersonBirthDateInvalidException.class);
    }

}
