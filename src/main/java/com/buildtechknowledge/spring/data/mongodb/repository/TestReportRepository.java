package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.TestReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestReportRepository extends MongoRepository<TestReport, String> {
    List<TestReport> findByProjectID(String projectID);
}

