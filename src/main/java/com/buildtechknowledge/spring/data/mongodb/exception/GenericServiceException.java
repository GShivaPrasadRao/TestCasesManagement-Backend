package com.buildtechknowledge.spring.data.mongodb.exception;

public class GenericServiceException extends RuntimeException {
    public GenericServiceException(String message) {
        super(message);
    }
}
