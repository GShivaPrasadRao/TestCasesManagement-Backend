package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestCaseRepository extends MongoRepository<TestCase, String> {
    //    List<TestCase> findBySprint(String sprint);

    Optional<TestCase> findByTestCaseID(String testCaseID);

    List<TestCase> findByAutomationStatus(String automationStatus);

    void deleteByTestCaseID(String testCaseID);


}
