package com.sccon.geospatial.person.application.service;

import com.sccon.geospatial.person.domain.model.Person;
import com.sccon.geospatial.person.domain.port.PersonRepository;
import com.sccon.geospatial.person.factory.PersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get All Person Service Tests")
class GetAllPersonServiceTest {

    @InjectMocks
    private GetAllPersonService getAllPersonService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should return all persons sorted by name")
    void shouldReturnAllPersonsSortedByName() {
        var person1 = PersonFactory.createPersonWithId(1L);
        var person2 = Person.create(2L, "Alice Smith", person1.birthDate().value(), person1.hireDate().value());
        var person3 = Person.create(3L, "Bob Johnson", person1.birthDate().value(), person1.hireDate().value());

        when(personRepository.findAll()).thenReturn(List.of(person1, person2, person3));

        var response = getAllPersonService.execute();

        assertThat(response).isNotNull()
            .hasSize(3);

        assertThat(response.get(0).name()).isEqualTo("Alice Smith");
        assertThat(response.get(1).name()).isEqualTo("Bob Johnson");
        assertThat(response.get(2).name()).isEqualTo("John Doe");

        verify(personRepository).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no persons exist")
    void shouldReturnEmptyListWhenNoPersonsExist() {
        when(personRepository.findAll()).thenReturn(List.of());

        var response = getAllPersonService.execute();

        assertThat(response).isNotNull()
            .isEmpty();

        verify(personRepository).findAll();
    }

}
