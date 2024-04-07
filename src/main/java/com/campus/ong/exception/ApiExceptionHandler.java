package com.campus.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.campus.ong.common.StandarizedApiExceptionResponse;


@RestControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(BussinesRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleBussinesRuleException(BussinesRuleException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion",ex.getCode(),ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

}