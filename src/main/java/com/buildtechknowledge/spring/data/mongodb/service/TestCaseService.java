package com.buildtechknowledge.spring.data.mongodb.service;

import com.buildtechknowledge.spring.data.mongodb.message.ApiResponse;
import com.buildtechknowledge.spring.data.mongodb.message.ResponseMessage;
import com.buildtechknowledge.spring.data.mongodb.model.TestCase;

import java.util.List;
import java.util.Optional;

public interface TestCaseService {
    ResponseMessage createTestCase(TestCase testCase);

    List<TestCase> getAllTestCases();

    Optional<TestCase> getTestCaseById(String testCaseID);

    TestCase updateTestCaseByTestCaseID(String testCaseID, TestCase updatedTestCase);

    String deleteTestCaseByTestCaseID(String testCaseID);

    ApiResponse<String> deleteAllTestCases();

    List<TestCase> getTestCasesByAutomationStatus(String automationStatus);
}