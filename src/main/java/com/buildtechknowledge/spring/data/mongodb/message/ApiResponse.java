package com.buildtechknowledge.spring.data.mongodb.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private int httpStatus;
    private String errorCode;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(String message, T data, int httpStatus, String errorCode) {
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }


}
