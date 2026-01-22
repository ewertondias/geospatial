package com.sccon.geospatial.person.adapter.in.api.handler;

import com.sccon.geospatial.person.domain.exception.PersonAgeOutputInvalidException;
import com.sccon.geospatial.person.domain.exception.PersonAlreadyExistsException;
import com.sccon.geospatial.person.domain.exception.PersonBirthDateInvalidException;
import com.sccon.geospatial.person.domain.exception.PersonHireDateInvalidException;
import com.sccon.geospatial.person.domain.exception.PersonIdInvalidException;
import com.sccon.geospatial.person.domain.exception.PersonNotFoundException;
import com.sccon.geospatial.person.domain.exception.PersonSalaryOutputInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersonExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PersonExceptionHandler.class);

    @ExceptionHandler(PersonAgeOutputInvalidException.class)
    public ResponseEntity<ProblemDetail> handlePersonAgeOutputInvalid(PersonAgeOutputInvalidException ex) {
        log.error("Person age output invalid: {}", ex.getMessage());

        var httpStatus = HttpStatus.BAD_REQUEST;
        var message = String.format("The value output '%s' is invalid for age.", ex.getMessage());
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handlePersonAlreadyExists(PersonAlreadyExistsException ex) {
        log.error("Person already exists: {}", ex.getMessage());

        var httpStatus = HttpStatus.CONFLICT;
        var message = String.format("Person with id '%s' already exists.", ex.getMessage());
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

    @ExceptionHandler(PersonBirthDateInvalidException.class)
    public ResponseEntity<ProblemDetail> handlePersonBirthDateInvalid(PersonBirthDateInvalidException ex) {
        log.error("Person birth date is invalid: {}", ex.getMessage());

        var httpStatus = HttpStatus.BAD_REQUEST;
        var message = "Person birth date is invalid";
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

    @ExceptionHandler(PersonHireDateInvalidException.class)
    public ResponseEntity<ProblemDetail> handlePersonHireDateInvalid(PersonHireDateInvalidException ex) {
        log.error("Person hire date is invalid: {}", ex.getMessage());

        var httpStatus = HttpStatus.BAD_REQUEST;
        var message = "Person hire date is invalid";
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

    @ExceptionHandler(PersonIdInvalidException.class)
    public ResponseEntity<ProblemDetail> handlePersonIdInvalid(PersonIdInvalidException ex) {
        log.error("Person id is invalid: {}", ex.getMessage());

        var httpStatus = HttpStatus.BAD_REQUEST;
        var message = "Person id is invalid";
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ProblemDetail> handlePersonNotFound(PersonNotFoundException ex) {
        log.error("Person not found: {}", ex.getMessage());

        var httpStatus = HttpStatus.NOT_FOUND;
        var message = String.format("Person with id '%s' does not exist.", ex.getMessage());
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

    @ExceptionHandler(PersonSalaryOutputInvalidException.class)
    public ResponseEntity<ProblemDetail> handlePersonSalaryOutputInvalid(PersonSalaryOutputInvalidException ex) {
        log.error("Person salary output invalid: {}", ex.getMessage());

        var httpStatus = HttpStatus.BAD_REQUEST;
        var message = String.format("The value output '%s' is invalid for salary.", ex.getMessage());
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

}
