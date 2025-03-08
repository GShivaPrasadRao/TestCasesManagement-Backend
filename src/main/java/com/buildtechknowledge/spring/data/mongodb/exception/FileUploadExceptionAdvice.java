package com.buildtechknowledge.spring.data.mongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    // Custom handler for MaxUploadSizeExceededException
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        // Provide a descriptive response here
        String errorMessage = "File size exceeds the maximum upload limit!";
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorMessage);
    }
}