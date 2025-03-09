// src/main/java/com/testmanagement/exception/GlobalExceptionHandler.java
package com.buildtechknowledge.spring.data.mongodb.exception;

import com.buildtechknowledge.spring.data.mongodb.message.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    //Invalid Input
//    @ExceptionHandler(InvalidInputException.class)
//    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }

    //    @ExceptionHandler(GenericServiceException.class)
//    public ResponseEntity<String> handleGenericServiceException(GenericServiceException ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorMessage> handleInvalidInputException(InvalidInputException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "Ensure project name is not empty and follows the correct format."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(GenericServiceException.class)
    public ResponseEntity<ErrorMessage> handleGenericServiceException(GenericServiceException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                "An unexpected error occurred. Please try again later."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorMessage> handleDuplicateResourceException(DuplicateResourceException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                "A project with this name already exists. Choose a unique name."
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }
//
//    //Duplicate Resource Exception
//    @ExceptionHandler(DuplicateResourceException.class)
//    public ResponseEntity<String> handleDuplicateResourceException(DuplicateResourceException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDatabaseConstraintViolation(DataIntegrityViolationException ex) {
        String message = extractFieldFromConstraintViolation(ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    private String extractFieldFromConstraintViolation(DataIntegrityViolationException ex) {
        String defaultMessage = "A unique constraint violation occurred.";

        if (ex.getRootCause() != null) {
            String rootMessage = ex.getRootCause().getMessage();

            // Extract field name dynamically
            Pattern pattern = Pattern.compile(".*\\((.*?)\\)=\\(.*?\\).*");
            Matcher matcher = pattern.matcher(rootMessage);
            if (matcher.find()) {
                return "The value for '" + matcher.group(1) + "' must be unique.";
            }
        }

        return defaultMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
