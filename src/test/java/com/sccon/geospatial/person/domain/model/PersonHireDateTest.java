package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonHireDateInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayName("Person Hire Date Domain Model Tests")
class PersonHireDateTest {

    @Test
    @DisplayName("Should create hire date successfully with valid date")
    void shouldCreateHireDateSuccessfully() {
        var hireDate = LocalDate.of(2020, 1, 1);

        var personHireDate = PersonHireDate.of(hireDate);

        assertThat(personHireDate).isNotNull();
        assertThat(personHireDate.value()).isEqualTo(hireDate);
    }

    @Test
    @DisplayName("Should throw exception when hire date is null")
    void shouldThrowExceptionWhenHireDateIsNull() {
        assertThatThrownBy(() -> PersonHireDate.of(null))
            .isInstanceOf(PersonHireDateInvalidException.class);
    }

    @Test
    @DisplayName("Should throw exception when hire date is in the future")
    void shouldThrowExceptionWhenHireDateIsInTheFuture() {
        var futureDate = LocalDate.now().plusDays(1);

        assertThatThrownBy(() -> PersonHireDate.of(futureDate))
            .isInstanceOf(PersonHireDateInvalidException.class);
    }

}
