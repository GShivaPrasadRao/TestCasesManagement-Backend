package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import com.buildtechknowledge.spring.data.mongodb.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    public List<TestCase> getAllTestCases() {
        return testCaseRepository.findAll();
    }

    public Optional<TestCase> getTestCaseById(String id) {

        return testCaseRepository.findById(id);
    }

    public TestCase createTestCase(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }

    public TestCase updateTestCase(String id, TestCase updatedTestCase) {

        // Throw exception if TestCase with the given id is not found
        TestCase existingTestCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TestCase not found with id: " + id));

        existingTestCase.setModule(updatedTestCase.getModule());
        existingTestCase.setScreenName(updatedTestCase.getScreenName());
        existingTestCase.setTestCaseName(updatedTestCase.getTestCaseName());
        existingTestCase.setDescription(updatedTestCase.getDescription());
        existingTestCase.setType(updatedTestCase.getType());
        existingTestCase.setExpectedResult(updatedTestCase.getExpectedResult());
        existingTestCase.setAutomationStatus(updatedTestCase.getAutomationStatus());
        existingTestCase.setPriority(updatedTestCase.getPriority());
        existingTestCase.setComments(updatedTestCase.getComments());
        existingTestCase.setUpdatedBy(updatedTestCase.getUpdatedBy());
        existingTestCase.setUpdatedDate(updatedTestCase.getUpdatedDate());
        existingTestCase.setVersion(updatedTestCase.getVersion());
        return testCaseRepository.save(existingTestCase);

    }


    public void deleteTestCase(String id) {
        // Throw exception if TestCase with the given id is not found
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TestCase not found with id: " + id));

        testCaseRepository.delete(testCase);
    }
}
