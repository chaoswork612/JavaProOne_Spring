package org.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.payment.dto.ResponseErrorDto;
import org.payment.exception.IntegrationException;
import org.payment.exception.PaymentException;
import org.payment.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseErrorDto handleException(PaymentException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

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

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDto handleException(RestClientException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseErrorDto handleException(IntegrationException ex){
        log.error(ex.getMessage(), ex);
        return new ResponseErrorDto(HttpStatus.BAD_GATEWAY.value(), ex.getMessage());
    }
}
