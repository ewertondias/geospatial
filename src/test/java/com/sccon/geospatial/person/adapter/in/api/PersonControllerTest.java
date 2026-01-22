package com.sccon.geospatial.person.adapter.in.api;

import com.sccon.geospatial.person.adapter.in.api.dto.CreatePersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonAgeResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.PersonSalaryResponse;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdateDetailPersonRequest;
import com.sccon.geospatial.person.adapter.in.api.dto.UpdatePersonRequest;
import com.sccon.geospatial.person.application.CreatePersonUseCase;
import com.sccon.geospatial.person.application.DeletePersonUseCase;
import com.sccon.geospatial.person.application.GetAllPersonUseCase;
import com.sccon.geospatial.person.application.GetPersonAgeByIdUseCase;
import com.sccon.geospatial.person.application.GetPersonByIdUseCase;
import com.sccon.geospatial.person.application.GetPersonSalaryByIdUseCase;
import com.sccon.geospatial.person.application.UpdatePersonDetailUseCase;
import com.sccon.geospatial.person.application.UpdatePersonUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Person Controller Tests")
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private GetAllPersonUseCase getAllPersonUseCase;

    @Mock
    private CreatePersonUseCase createPersonUseCase;

    @Mock
    private DeletePersonUseCase deletePersonUseCase;

    @Mock
    private UpdatePersonUseCase updatePersonUseCase;

    @Mock
    private UpdatePersonDetailUseCase updatePersonDetailUseCase;

    @Mock
    private GetPersonByIdUseCase getPersonByIdUseCase;

    @Mock
    private GetPersonAgeByIdUseCase getPersonAgeByIdUseCase;

    @Mock
    private GetPersonSalaryByIdUseCase getPersonSalaryByIdUseCase;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(8080);
        request.setRequestURI("/persons");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @AfterEach
    void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    @DisplayName("Should get all persons successfully")
    void shouldGetAllPersonsSuccessfully() {
        var persons = List.of(
            new PersonResponse(1L, "John Doe", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1)),
            new PersonResponse(2L, "Jane Doe", LocalDate.of(1995, 5, 15), LocalDate.of(2021, 6, 20))
        );

        when(getAllPersonUseCase.execute()).thenReturn(persons);

        var response = personController.getAll();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(2);
        verify(getAllPersonUseCase).execute();
    }

    @Test
    @DisplayName("Should create person successfully")
    void shouldCreatePersonSuccessfully() {
        var request = new CreatePersonRequest(
            1L,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        var personResponse = new PersonResponse(
            1L,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        when(createPersonUseCase.execute(request)).thenReturn(personResponse);

        var response = personController.create(request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(1L);

        verify(createPersonUseCase).execute(request);
    }

    @Test
    @DisplayName("Should delete person successfully")
    void shouldDeletePersonSuccessfully() {
        var id = 1L;

        var response = personController.delete(id);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
        verify(deletePersonUseCase).execute(id);
    }

    @Test
    @DisplayName("Should update person successfully")
    void shouldUpdatePersonSuccessfully() {
        var id = 1L;
        var request = new UpdatePersonRequest(
            "Jane Doe",
            LocalDate.of(1995, 5, 15),
            LocalDate.of(2021, 6, 20)
        );
        var personResponse = new PersonResponse(
            id,
            "Jane Doe",
            LocalDate.of(1995, 5, 15),
            LocalDate.of(2021, 6, 20)
        );

        when(updatePersonUseCase.execute(id, request)).thenReturn(personResponse);

        var response = personController.update(id, request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().name()).isEqualTo("Jane Doe");
        verify(updatePersonUseCase).execute(id, request);
    }

    @Test
    @DisplayName("Should update person detail successfully")
    void shouldUpdatePersonDetailSuccessfully() {
        var id = 1L;
        var request = new UpdateDetailPersonRequest(
            "Jane Doe",
            null,
            null
        );

        var personResponse = new PersonResponse(
            id,
            "Jane Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        when(updatePersonDetailUseCase.execute(id, request)).thenReturn(personResponse);

        var response = personController.updateDetail(id, request);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        verify(updatePersonDetailUseCase).execute(id, request);
    }

    @Test
    @DisplayName("Should get person by id successfully")
    void shouldGetPersonByIdSuccessfully() {
        var id = 1L;
        var personResponse = new PersonResponse(
            id,
            "John Doe",
            LocalDate.of(1990, 1, 1),
            LocalDate.of(2020, 1, 1)
        );

        when(getPersonByIdUseCase.execute(id)).thenReturn(personResponse);

        var response = personController.getById(id);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(id);

        verify(getPersonByIdUseCase).execute(id);
    }

    @Test
    @DisplayName("Should get person age successfully")
    void shouldGetPersonAgeSuccessfully() {
        var id = 1L;
        var output = "years";
        var ageResponse = new PersonAgeResponse(30L);

        when(getPersonAgeByIdUseCase.execute(id, output)).thenReturn(ageResponse);

        var response = personController.getAge(id, output);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().age()).isEqualTo(30L);

        verify(getPersonAgeByIdUseCase).execute(id, output);
    }

    @Test
    @DisplayName("Should get person salary successfully")
    void shouldGetPersonSalarySuccessfully() {
        var id = 1L;
        var output = "full";
        var salaryResponse = new PersonSalaryResponse("R$ 5.000,00");

        when(getPersonSalaryByIdUseCase.execute(id, output)).thenReturn(salaryResponse);

        var response = personController.getSalary(id, output);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().salary()).isEqualTo("R$ 5.000,00");

        verify(getPersonSalaryByIdUseCase).execute(id, output);
    }

}
