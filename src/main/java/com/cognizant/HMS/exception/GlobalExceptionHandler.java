package com.cognizant.HMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidServiceException.class)
    public ResponseEntity<ErrorResponse> handlePatientNotFound(InvalidServiceException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), "Service LookUp failed");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBillNotFound(BillNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), "Bill Not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
}
