package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.TestData;
import com.buildtechknowledge.spring.data.mongodb.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestDataService {

    @Autowired
    private TestDataRepository testDataRepository;

    public List<TestData> getAllTestData() {
        return testDataRepository.findAll();
    }

    public Optional<TestData> getTestDataById(String id) {
        return testDataRepository.findById(id);
    }

    public TestData createTestData(TestData testData) {
        return testDataRepository.save(testData);
    }

    public TestData updateTestData(String id, TestData updatedTestData) {
        return testDataRepository.findById(id)
                .map(testData -> {
                    testData.setProjectName(updatedTestData.getProjectName());
                    testData.setConnectorCategory(updatedTestData.getConnectorCategory());
                    testData.setConnectorType(updatedTestData.getConnectorType());
                    // Update additional fields as needed
                    return testDataRepository.save(testData);
                }).orElseGet(() -> {
                    updatedTestData.setId(id);
                    return testDataRepository.save(updatedTestData);
                });
    }

    public void deleteTestData(String id) {
        testDataRepository.deleteById(id);
    }
}
