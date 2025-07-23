package com.cognizant.HMS.exception;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private String details;
    private LocalDate timestamp;

    public ErrorResponse(String message, String details){
        this.message = message;
        this.details = details;
        this.timestamp = LocalDate.now();
    }
    
}
