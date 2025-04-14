package com.buildtechknowledge.spring.data.mongodb.controller;

import com.buildtechknowledge.spring.data.mongodb.implementation.TestCaseServiceImpl;
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
    public ResponseEntity<?> createTestCase(@Valid @RequestBody TestCase testCase) {
        // TestCase createdTestCase = testCaseServiceImpl.createTestCase(testCase);
        ResponseMessage savedTestCase = testCaseServiceImpl.createTestCase(testCase);
        return new ResponseEntity<>(savedTestCase, HttpStatus.CREATED);
    }

    /**
     * Endpoint to fetch all test cases.
     *
     * @return List of all test cases.
     */
    @GetMapping
    public ResponseEntity<List<TestCase>> getAllTestCases() {
        List<TestCase> testCases = testCaseServiceImpl.getAllTestCases();
        return ResponseEntity.ok(testCases);
    }

    /**
     * Endpoint to fetch a test case by its ID.
     *
     * @param testCaseID of the test case to retrieve.
     * @return Test case if found, or 404 if not.
     */
    @GetMapping("/{testCaseID}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable String testCaseID) {
        Optional<TestCase> testCase = testCaseServiceImpl.getTestCaseById(testCaseID);
        return testCase.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to fetch a test case by its ID.
     *
     * @param automationStatus of the test case to retrieve.
     * @return Test case if found, or 404 if not.
     */
    @GetMapping("/automationStatus/{automationStatus}")
    public ResponseEntity<List<TestCase>> getTestCaseByAutomationStatus(@PathVariable String automationStatus) {
        List<TestCase> testCases = testCaseServiceImpl.getTestCasesByAutomationStatus(automationStatus);
        if (testCases.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(testCases, HttpStatus.OK);
    }

    /**
     * Endpoint to update an existing test case.
     *
     * @param testCaseID ID of the test case to update.
     * @param testCaseID Updated test case details.
     * @return Updated test case if found; 404 otherwise.
     */
    @PutMapping("/{testCaseID}")
    public ResponseEntity<TestCase> updateTestCase(
            @PathVariable String testCaseID,
            @RequestBody TestCase updatedTestCase) {

        TestCase updated = testCaseServiceImpl.updateTestCaseByTestCaseID(testCaseID, updatedTestCase);
        return ResponseEntity.ok(updated);
    }


    /**
     * Endpoint to delete a specific test case.
     *
     * @param testCaseID of the test case to delete.
     * @return Response indicating the result of the operation.
     */
    @DeleteMapping("/{testCaseID}")
    public ResponseEntity<Map<String, String>> deleteTestCaseByTestCaseID(@PathVariable String testCaseID) {
        String message = testCaseServiceImpl.deleteTestCaseByTestCaseID(testCaseID);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

}