package com.sccon.geospatial.person.domain.model;

import com.sccon.geospatial.person.domain.exception.PersonHireDateInvalidException;
import com.sccon.geospatial.person.factory.PersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayName("Person Domain Model Tests")
class PersonTest {

    @Test
    @DisplayName("Should create person successfully with valid data")
    void shouldCreatePersonSuccessfully() {
        var id = 1L;
        var name = "John Doe";
        var birthDate = LocalDate.of(1990, 1, 1);
        var hireDate = LocalDate.of(2020, 1, 1);

        var person = Person.create(id, name, birthDate, hireDate);

        assertThat(person).isNotNull();
        assertThat(person.id().value()).isEqualTo(id);
        assertThat(person.name()).isEqualTo(name);
        assertThat(person.birthDate().value()).isEqualTo(birthDate);
        assertThat(person.hireDate().value()).isEqualTo(hireDate);
    }

    @Test
    @DisplayName("Should throw exception when hire date is before birth date")
    void shouldThrowExceptionWhenHireDateIsBeforeBirthDate() {
        var id = 1L;
        var name = "John Doe";
        var birthDate = LocalDate.of(1990, 1, 1);
        var hireDate = LocalDate.of(1980, 1, 1);

        assertThatThrownBy(() -> Person.create(id, name, birthDate, hireDate))
            .isInstanceOf(PersonHireDateInvalidException.class);
    }

    @Test
    @DisplayName("Should update person successfully")
    void shouldUpdatePersonSuccessfully() {
        var person = PersonFactory.createDefaultPerson();
        var newName = "Jane Doe";
        var newBirthDate = LocalDate.of(1995, 5, 15);
        var newHireDate = LocalDate.of(2021, 6, 20);

        var updatedPerson = person.update(newName, newBirthDate, newHireDate);

        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.id().value()).isEqualTo(person.id().value());
        assertThat(updatedPerson.name()).isEqualTo(newName);
        assertThat(updatedPerson.birthDate().value()).isEqualTo(newBirthDate);
        assertThat(updatedPerson.hireDate().value()).isEqualTo(newHireDate);
    }

    @Test
    @DisplayName("Should update person detail with partial data")
    void shouldUpdatePersonDetailWithPartialData() {
        var person = PersonFactory.createDefaultPerson();
        var newName = "Jane Doe";

        var updatedPerson = person.updateDetail(newName, null, null);

        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.id().value()).isEqualTo(person.id().value());
        assertThat(updatedPerson.name()).isEqualTo(newName);
        assertThat(updatedPerson.birthDate().value()).isEqualTo(person.birthDate().value());
        assertThat(updatedPerson.hireDate().value()).isEqualTo(person.hireDate().value());
    }

    @Test
    @DisplayName("Should update person detail with null name keeping original")
    void shouldUpdatePersonDetailWithNullNameKeepingOriginal() {
        var person = PersonFactory.createDefaultPerson();
        var originalName = person.name();
        var newBirthDate = LocalDate.of(1995, 5, 15);

        var updatedPerson = person.updateDetail(null, newBirthDate, null);

        assertThat(updatedPerson).isNotNull();
        assertThat(updatedPerson.name()).isEqualTo(originalName);
        assertThat(updatedPerson.birthDate().value()).isEqualTo(newBirthDate);
    }

}
