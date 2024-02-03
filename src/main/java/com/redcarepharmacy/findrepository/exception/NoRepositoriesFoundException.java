package com.redcarepharmacy.findrepository.exception;

public class NoRepositoriesFoundException extends RuntimeException{

    public NoRepositoriesFoundException(String message) {
        super(message);
    }
}