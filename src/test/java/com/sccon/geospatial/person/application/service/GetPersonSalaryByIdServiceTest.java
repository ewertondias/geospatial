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
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Get Person Salary By Id Service Tests")
class GetPersonSalaryByIdServiceTest {

    @InjectMocks
    private GetPersonSalaryByIdService getPersonSalaryByIdService;

    @Mock
    private PersonRepository personRepository;

    @Test
    @DisplayName("Should return full salary")
    void shouldReturnFullSalary() {
        var id = 1L;
        var hireDate = LocalDate.now().minusYears(2);
        var person = PersonFactory.createPersonWithDates(LocalDate.of(1990, 1, 1), hireDate);

        ReflectionTestUtils.setField(getPersonSalaryByIdService, "minSalary", new BigDecimal("1000.00"));
        ReflectionTestUtils.setField(getPersonSalaryByIdService, "initialSalary", new BigDecimal("5000.00"));
        ReflectionTestUtils.setField(getPersonSalaryByIdService, "yearlyIncreasePercentage", new BigDecimal("1.05"));
        ReflectionTestUtils.setField(getPersonSalaryByIdService, "yearlyIncreaseValue", new BigDecimal("500.00"));

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        var response = getPersonSalaryByIdService.execute(id, "full");

        assertThat(response).isNotNull();
        assertThat(response.salary()).isNotNull();
        assertThat(response.salary()).contains("R$");
        verify(personRepository).findById(any(PersonId.class));
    }

    @Test
    @DisplayName("Should return min salary")
    void shouldReturnMinSalary() {
        var id = 1L;
        var hireDate = LocalDate.now().minusYears(1);
        var person = PersonFactory.createPersonWithDates(LocalDate.of(1990, 1, 1), hireDate);

        ReflectionTestUtils.setField(getPersonSalaryByIdService, "minSalary", new BigDecimal("1000.00"));
        ReflectionTestUtils.setField(getPersonSalaryByIdService, "initialSalary", new BigDecimal("5000.00"));
        ReflectionTestUtils.setField(getPersonSalaryByIdService, "yearlyIncreasePercentage", new BigDecimal("1.05"));
        ReflectionTestUtils.setField(getPersonSalaryByIdService, "yearlyIncreaseValue", new BigDecimal("500.00"));

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.of(person));

        var response = getPersonSalaryByIdService.execute(id, "min");

        assertThat(response).isNotNull();
        assertThat(response.salary()).isNotNull();
        verify(personRepository).findById(any(PersonId.class));
    }

    @Test
    @DisplayName("Should throw exception when person not found")
    void shouldThrowExceptionWhenPersonNotFound() {
        var id = 1L;

        when(personRepository.findById(any(PersonId.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getPersonSalaryByIdService.execute(id, "full"))
            .isInstanceOf(PersonNotFoundException.class);

        verify(personRepository).findById(any(PersonId.class));
    }

}
