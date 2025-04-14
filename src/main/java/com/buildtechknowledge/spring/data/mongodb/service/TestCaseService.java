package com.buildtechknowledge.spring.data.mongodb.service;

import com.buildtechknowledge.spring.data.mongodb.message.ResponseMessage;
import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import org.apache.catalina.connector.Response;

import java.util.List;
import java.util.Optional;

public interface TestCaseService {
    ResponseMessage createTestCase(TestCase testCase);

    List<TestCase> getAllTestCases();

    Optional<TestCase> getTestCaseById(String testCaseID);

    TestCase updateTestCaseByTestCaseID(String testCaseID, TestCase updatedTestCase);

    String deleteTestCaseByTestCaseID(String testCaseID);

    List<TestCase> getTestCasesByAutomationStatus(String automationStatus);
}