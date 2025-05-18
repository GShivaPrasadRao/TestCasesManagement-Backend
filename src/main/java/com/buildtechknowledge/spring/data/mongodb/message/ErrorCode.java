package com.buildtechknowledge.spring.data.mongodb.message;

public class ErrorCode {
    // TestCase specific error codes
    public static final String ENTITY_NOT_FOUND = "ENTITY_404";
    public static final String ENTITY_CREATE_FAILED = "ENTITY_500";
    public static final String ENTITY_UPDATE_FAILED = "ENTITY_500";
    public static final String ENTITY_DELETION_FAILED = "ENTITY_500";

    // Validation error codes
    public static final String INVALID_INPUT = "VALIDATION_400";

    // Authorization error codes
    public static final String UNAUTHORIZED_ACCESS = "AUTH_401";

    // Internal server errors
    public static final String INTERNAL_SERVER_ERROR = "SERVER_500";
}
