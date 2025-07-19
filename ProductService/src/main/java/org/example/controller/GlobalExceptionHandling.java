package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ResponseErrorDto;
import org.example.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDto handleException(RuntimeException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDto handleException(ProductNotFoundException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
