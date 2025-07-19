package org.limit.controller;

import lombok.extern.slf4j.Slf4j;
import org.limit.exception.ProcessPaymentException;
import org.limit.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ProcessPaymentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDto handleException(ProcessPaymentException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDto handleException(RuntimeException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
    }
}
