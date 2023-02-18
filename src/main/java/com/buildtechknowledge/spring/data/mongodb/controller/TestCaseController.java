package com.buildtechknowledge.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.model.Tutorial;
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

import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import com.buildtechknowledge.spring.data.mongodb.repository.TestCaseRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TestCaseController {

    @Autowired
    TestCaseRepository testCaseRepository;

    //List all Test Cases
    @GetMapping("/testcases")
    public List<TestCase> getAllTestCases() {
        System.out.println("********Get List of testcases ********");

        return testCaseRepository.findAll();
    }

    //Create Test Case
    @PostMapping("/testcases")
    public ResponseEntity<TestCase> createTestCase(@RequestBody TestCase testCaseDetails) {
        System.out.println("*****Create Test Case*********");

        try {
            TestCase testCase = testCaseRepository.save(testCaseDetails);
            return new ResponseEntity<>(testCase, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Get Test Case by Id
    @GetMapping("/testcases/id/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable("id") String id) {

//        System.out.println("*****Get Test Case by ID*********");
//
//        TestCase testCase = testCaseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException
//                        ("Test Case not exist with id :" + id));
//        return ResponseEntity.ok(testCase);


        Optional<TestCase> testCase = testCaseRepository.findById(id);

        if (testCase.isPresent()) {
            return new ResponseEntity<>(testCase.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Update Test Case
    @PutMapping("/testcases/{id}")
    public ResponseEntity<TestCase> updateTestCase(@PathVariable("id") String id, @RequestBody TestCase testCaseDetails) {

        System.out.println("*****Update Test Case by ID*********");
          TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Test Case not exist with id :" + id));

        testCase.setModule(testCaseDetails.getModule());
        testCase.setScreenName(testCaseDetails.getScreenName());
        testCase.setName(testCaseDetails.getName());
        testCase.setDescription(testCaseDetails.getDescription());
        testCase.setStatus(testCaseDetails.getStatus());
        testCase.setType(testCaseDetails.getType());
        testCase.setComments(testCaseDetails.getComments());
        TestCase updatedTestCase = testCaseRepository.save(testCase);

        return ResponseEntity.ok(updatedTestCase);

    }

    //Delete test case by ID
    @DeleteMapping("/testcases/id/{id}")
    public ResponseEntity<HttpStatus> deleteTestCase(@PathVariable("id") String id) {
        try {
            System.out.println("*****Delete Test Case by ID*********");

            testCaseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete all Test Cases
    @DeleteMapping("/testcases")
    public ResponseEntity<HttpStatus> deleteAllTestCases() {
        try {
            System.out.println("*****Delete all Test Cases*********");

            testCaseRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get Test Cases by status
    @GetMapping("/testcases/status/{status}")
    public ResponseEntity<List<TestCase>> findByStatus(@PathVariable String status) {

        try {
            System.out.println("*****Test Case By Status*********");
            List<TestCase> testCases = testCaseRepository.findByStatus(status);

            if (testCases.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(testCases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/testcases/")
//    public List<TestCase> getTestCaseByStatus(@RequestParam(value="status") String status ){
//        return testCaseRepository.findByStatus(status);
//    }

}
