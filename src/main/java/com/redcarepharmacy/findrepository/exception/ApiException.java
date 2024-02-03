package com.redcarepharmacy.findrepository.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException extends RuntimeException{

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp){
    }
}