package com.sccon.geospatial.infrastructure.handler;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Validation failed: {}", ex.getMessage());

        var problemDetail = ProblemDetail.forStatusAndDetail(status, "Validation failed");

        var errors = new ArrayList<Map<String, String>>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> {
                var errorMap = new HashMap<String, String>();
                errorMap.put("field", error.getField());
                errorMap.put("message", error.getDefaultMessage());

                errors.add(errorMap);
            });

        problemDetail.setProperty("errors", errors);

        return ResponseEntity.status(status)
            .body(problemDetail);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Illegal argument: {}", ex.getMessage());

        var httpStatus = HttpStatus.BAD_REQUEST;
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, ex.getMessage());

        return ResponseEntity.status(httpStatus)
            .body(problemDetail);
    }

}
