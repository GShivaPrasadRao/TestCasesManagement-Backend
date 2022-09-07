package com.bezkoder.spring.data.mongodb.repository;

import com.bezkoder.spring.data.mongodb.model.TestCase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestCaseRepository extends MongoRepository<TestCase,String> {
    List<TestCase> findBySprint(String sprint);
    List<TestCase> findByStatus(String status);
}
