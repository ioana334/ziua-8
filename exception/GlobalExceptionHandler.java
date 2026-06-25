package com.company.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.map;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>>handlerNotFound(ResourceNotFoundException ex) {
        Map<String, String>body = new HashMap<>();
        body.put("error",ex.getMessage() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
     @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String,String>>handlerBusiness(BusinessException ex  ) {
        Map<String, String>body = new HashMap<>();
        body.put("error",ex.getMessage() );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handlerValidation(MethodArgumentNotValidException ex) {
        Map<String, String>errors = new HashMap<>();
        ex.getBildingREsult().getFieldErrors()
           .forEach(fieldErrors->errors.put(fieldErrors.getFiled(),fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
