package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.TestResult;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestResultRepository extends MongoRepository<TestResult, String> {
    List<TestResult> findByProjectID(String projectID);

    long countByProjectIDAndStatus(String projectID, String status);

    @Aggregation(pipeline = {
            "{ $match: { projectID: ?0 } }",
            "{ $group: { _id: \"$status\", count: { $sum: 1 } } }"
    })
    List<Map<String, Object>> getStatusCounts(String projectID);
}
