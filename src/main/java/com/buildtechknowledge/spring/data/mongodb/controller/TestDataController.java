package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.model.TestData;
import com.buildtechknowledge.spring.data.mongodb.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testdata")
public class TestDataController {

    @Autowired
    private TestDataService testDataService;

    @GetMapping
    public List<TestData> getAllTestData() {
        return testDataService.getAllTestData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestData> getTestDataById(@PathVariable String id) {
        return testDataService.getTestDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TestData createTestData(@RequestBody TestData testData) {
        return testDataService.createTestData(testData);
    }

    @PutMapping("/{id}")
    public TestData updateTestData(@PathVariable String id, @RequestBody TestData testData) {
        return testDataService.updateTestData(id, testData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestData(@PathVariable String id) {
        testDataService.deleteTestData(id);
        return ResponseEntity.noContent().build();
    }
}
