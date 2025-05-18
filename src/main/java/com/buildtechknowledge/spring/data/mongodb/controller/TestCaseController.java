package com.buildtechknowledge.spring.data.mongodb.controller;

import com.buildtechknowledge.spring.data.mongodb.implementation.TestCaseServiceImpl;
import com.buildtechknowledge.spring.data.mongodb.message.ApiResponse;
import com.buildtechknowledge.spring.data.mongodb.message.ErrorCode;
import com.buildtechknowledge.spring.data.mongodb.message.ResponseMessage;
import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import com.buildtechknowledge.spring.data.mongodb.service.TestCaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/testcases") // Base endpoint for accessing test cases
public class TestCaseController {

//    private final TestCaseService testCaseService;

    private final TestCaseServiceImpl testCaseServiceImpl;

    @Autowired
    public TestCaseController(TestCaseServiceImpl testCaseServiceImpl) {
        this.testCaseServiceImpl = testCaseServiceImpl;
    }

    /**
     * Endpoint to create a new test case.
     *
     * @param testCase Test case to be created.
     * @return Created test case.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<TestCase>> createTestCase(@Valid @RequestBody TestCase testCase) {
        try {
            ResponseMessage result = testCaseServiceImpl.createTestCase(testCase);
            ApiResponse<TestCase> response = new ApiResponse<>(
                    result.getMessage(),
                    testCase,
                    HttpStatus.CREATED.value(),
                    null
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<TestCase> response = new ApiResponse<>(
                    "Failed to create test case.",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ErrorCode.ENTITY_CREATE_FAILED // Use the error code for creation failure
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Endpoint to fetch all test cases.
     *
     * @return List of all test cases.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<TestCase>>> getAllTestCases() {
        List<TestCase> testCases = testCaseServiceImpl.getAllTestCases();
        ApiResponse<List<TestCase>> response;
        if (testCases.isEmpty()) {
            response = new ApiResponse<>(
                    "No test cases found.",
                    null,
                    HttpStatus.NO_CONTENT.value(),
                    ErrorCode.ENTITY_NOT_FOUND
            );
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            response = new ApiResponse<>(
                    "Test cases retrieved successfully.",
                    testCases,
                    HttpStatus.OK.value(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * Endpoint to fetch a test case by its ID.
     *
     * @param testCaseID of the test case to retrieve.
     * @return Test case if found, or 404 if not.
     */
    @GetMapping("/{testCaseID}")
    public ResponseEntity<?> getTestCaseById(@PathVariable String testCaseID) {
        Optional<TestCase> testCase = testCaseServiceImpl.getTestCaseById(testCaseID);

        if (testCase.isPresent()) {
            ApiResponse<TestCase> response = new ApiResponse<>(
                    "Test case found.",
                    testCase.get(),
                    HttpStatus.OK.value(),
                    null // No error code for success
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<TestCase> response = new ApiResponse<>(
                    "Test case with ID " + testCaseID + " not found.",
                    null,
                    HttpStatus.NOT_FOUND.value(),
                    ErrorCode.ENTITY_NOT_FOUND // Use the error code for not found
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Endpoint to fetch a test case by its ID.
     *
     * @param automationStatus of the test case to retrieve.
     * @return Test case if found, or 404 if not.
     */
    @GetMapping("/automationStatus/{automationStatus}")
    public ResponseEntity<ApiResponse<List<TestCase>>> getTestCaseByAutomationStatus(@PathVariable String automationStatus) {
        List<TestCase> testCases = testCaseServiceImpl.getTestCasesByAutomationStatus(automationStatus);
        ApiResponse<List<TestCase>> response;
        if (testCases.isEmpty()) {
            response = new ApiResponse<>(
                    "No test cases found with the given automation status.",
                    null,
                    HttpStatus.NO_CONTENT.value(),
                    ErrorCode.ENTITY_NOT_FOUND
            );
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            response = new ApiResponse<>(
                    "Test cases with the given automation status retrieved successfully.",
                    testCases,
                    HttpStatus.OK.value(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * Endpoint to update an existing test case.
     *
     * @param testCaseID ID of the test case to update.
     * @param testCaseID Updated test case details.
     * @return Updated test case if found; 404 otherwise.
     */
    @PutMapping("/{testCaseID}")
    public ResponseEntity<ApiResponse<TestCase>> updateTestCase(
            @PathVariable String testCaseID,
            @RequestBody TestCase updatedTestCase) {

        TestCase updated = testCaseServiceImpl.updateTestCaseByTestCaseID(testCaseID, updatedTestCase);
        ApiResponse<TestCase> response;

        if (updated == null) {
            response = new ApiResponse<>(
                    "Test case update failed.",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ErrorCode.ENTITY_UPDATE_FAILED
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response = new ApiResponse<>(
                    "Test case updated successfully.",
                    updated,
                    HttpStatus.OK.value(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    /**
     * Endpoint to delete a specific test case.
     *
     * @param testCaseID of the test case to delete.
     * @return Response indicating the result of the operation.
     */
    @DeleteMapping("/{testCaseID}")
    public ResponseEntity<ApiResponse<Void>> deleteTestCaseByTestCaseID(@PathVariable String testCaseID) {
        String deletionStatus = testCaseServiceImpl.deleteTestCaseByTestCaseID(testCaseID);
        if (deletionStatus.contains("deleted successfully")) {
            ApiResponse<Void> response = new ApiResponse<>(
                    "Test Case ID:"+testCaseID+" deleted successfully.",
                    null,
                    HttpStatus.OK.value(),
                    null
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Void> response = new ApiResponse<>(
                    "Failed to delete test case.",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ErrorCode.ENTITY_DELETION_FAILED // Use the error code for deletion failure
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping
    public ResponseEntity<ApiResponse<String>> deleteAllTestCases() {
        ApiResponse<String> response = testCaseServiceImpl.deleteAllTestCases();
        if ("No test cases available to delete.".equals(response.getMessage())) {
            response.setHttpStatus(HttpStatus.NOT_FOUND.value());
            response.setErrorCode(ErrorCode.ENTITY_NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.setHttpStatus(HttpStatus.OK.value());
            response.setErrorCode(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}

