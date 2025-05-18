package com.buildtechknowledge.spring.data.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.Date;


@Document(collection = "test_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {

    @Id
    private String id;

    private String projectID;
    private String testCaseID;
    private String testCaseName;

    private String status; // Pass | Fail | Skipped | Blocked
    private String executionDate;
    private String executedBy;
    private double executionTime;
    private String environment;
    private String comments;
    private String screenshotLink;
    private boolean automationTriggered;
}

