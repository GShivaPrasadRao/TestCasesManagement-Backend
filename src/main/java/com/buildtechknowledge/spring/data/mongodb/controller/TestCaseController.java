package com.bezkoder.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.data.mongodb.model.TestCase;
import com.bezkoder.spring.data.mongodb.repository.TestCaseRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TestCaseController {

    @Autowired
    TestCaseRepository testCaseRepository;

    @GetMapping("/testCases")
    public ResponseEntity<List<TestCase>> getAllTestCases(@RequestParam(required = false) String sprint) {
        try {
            List<TestCase> testCases = new ArrayList<TestCase>();

            if (sprint == null)
                testCaseRepository.findAll().forEach(testCases::add);
            else
                testCaseRepository.findBySprint(sprint).forEach(testCases::add);

            if (testCases.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(testCases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/testCases/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable("id") String id) {
        Optional<TestCase> testCaseData = testCaseRepository.findById(id);

        if (testCaseData.isPresent()) {
            return new ResponseEntity<>(testCaseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/testCases")
    public ResponseEntity<TestCase> createTestCase(@RequestBody TestCase testCase) {
        try {
            TestCase _testCase = testCaseRepository.save(testCase);
            return new ResponseEntity<>(_testCase, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/testCases/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable("id") String id, @RequestBody TestCase testCase) {
        Optional<TestCase> testCaseData = testCaseRepository.findById(id);

        if (testCaseData.isPresent()) {
            TestCase _testCase = testCaseData.get();
            _testCase.setScreenName(testCase.getScreenName());
            _testCase.setDescription(testCase.getDescription());
            _testCase.setSprint(testCase.getSprint());
            _testCase.setComments(testCase.getComments());
            _testCase.setStatus(testCase.getStatus());
            _testCase.setType(testCase.getType());

            return new ResponseEntity<>(testCaseRepository.save(_testCase), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/testCases/{id}")
    public ResponseEntity<HttpStatus> deleteTestCase(@PathVariable("id") String id) {
        try {
            testCaseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/testCases")
    public ResponseEntity<HttpStatus> deleteAllTestCases() {
        try {
            testCaseRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/testCases/published")
    public ResponseEntity<List<TestCase>> findByStatus(String status) {
        try {
            System.out.println();
            List<TestCase> testCases = testCaseRepository.findByStatus(status);

            if (testCases.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
