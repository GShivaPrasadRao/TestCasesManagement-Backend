package com.buildtechknowledge.spring.data.mongodb.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
