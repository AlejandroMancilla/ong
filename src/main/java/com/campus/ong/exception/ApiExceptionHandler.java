package com.campus.ong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.campus.ong.common.StandarizedApiExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BussinesRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleBussinesRuleException(BussinesRuleException ex) {
        String errorMessage = "There was a validation error in the application.. Detalles: " + ex.getMessage();
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validación", ex.getCode(), errorMessage);
        return new ResponseEntity<>(response, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleAccessDeniedException(AccessDeniedException ex) {
        String errorMessage = "You do not have access to this endpointt";
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Access Denied", "403", errorMessage);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}