package com.buildtechknowledge.spring.data.mongodb.implementation;

import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.message.ResponseMessage;
import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import com.buildtechknowledge.spring.data.mongodb.repository.TestCaseRepository;
import com.buildtechknowledge.spring.data.mongodb.service.SequenceGeneratorService;
import com.buildtechknowledge.spring.data.mongodb.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    private final TestCaseRepository testCaseRepository;

    @Autowired
    public TestCaseServiceImpl(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public ResponseMessage createTestCase(TestCase testCase) {
        // Generate an ID if none provided
        if (testCase.getTestCaseID() == null || testCase.getTestCaseID().trim().isEmpty()) {
            String generatedID = sequenceGeneratorService.generateID("testCase_sequence", "TESTCASE");
            testCase.setTestCaseID(generatedID);
        }
        testCaseRepository.save(testCase);
        return new ResponseMessage("TestCase created successfully");
    }

    @Override
    public List<TestCase> getAllTestCases() {
        return testCaseRepository.findAll();
    }

    @Override
    public Optional<TestCase> getTestCaseById(String testCaseID) {
        return testCaseRepository.findByTestCaseID(testCaseID);
    }


    @Override
    public List<TestCase> getTestCasesByAutomationStatus(String status) {
        return testCaseRepository.findByAutomationStatus(status);
    }

    @Override
    public TestCase updateTestCaseByTestCaseID(String testCaseID, TestCase updatedTestCase) {
        TestCase existing = testCaseRepository.findByTestCaseID(testCaseID)
                .orElseThrow(() -> new ResourceNotFoundException("TestCase with ID <" + testCaseID + "> not found"));

        // Update only desired fields
        existing.setModule(updatedTestCase.getModule());
        existing.setScreenName(updatedTestCase.getScreenName());
        existing.setTestCaseName(updatedTestCase.getTestCaseName());
        existing.setDescription(updatedTestCase.getDescription());
        existing.setType(updatedTestCase.getType());
        existing.setExpectedResult(updatedTestCase.getExpectedResult());
        existing.setAutomationStatus(updatedTestCase.getAutomationStatus());
        existing.setPriority(updatedTestCase.getPriority());
        existing.setComments(updatedTestCase.getComments());
        existing.setUpdatedBy(updatedTestCase.getUpdatedBy());
        existing.setUpdatedDate(LocalDateTime.now());
        existing.setVersion(updatedTestCase.getVersion());

        return testCaseRepository.save(existing);
    }

    @Override
    public String deleteTestCaseByTestCaseID(String testCaseID) {
        Optional<TestCase> testCase = testCaseRepository.findByTestCaseID(testCaseID);
        if (testCase.isPresent()) {
            testCaseRepository.deleteByTestCaseID(testCaseID);
            return "TestCase :" + testCaseID + " deleted successfully";
        } else {
            throw new ResourceNotFoundException("TestCase with ID <" + testCaseID + "> not found");
        }
    }


}