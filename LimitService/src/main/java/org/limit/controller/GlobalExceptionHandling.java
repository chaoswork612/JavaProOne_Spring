package org.limit.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.limit.exception.ReserveLimitException;
import org.limit.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ReserveLimitException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDto handleException(ReserveLimitException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDto handleException(RuntimeException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDto handleException(EntityNotFoundException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User Limits were not found");
    }
}
