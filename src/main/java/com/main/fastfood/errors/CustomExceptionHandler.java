package com.main.fastfood.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorDetails customErrorDetails(ConstraintViolationException ex) {
        return new CustomErrorDetails(
                new Date(),
                "Constraint Violation (Global) | Custom Exception",
                ex.getMessage()
        );
    }



}
