package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "test_reports")
public class TestReport {

    @Id
    private String id;

    private String projectID;
    private String reportName;

    private String executionDate; // keep as string
    private String generatedBy;
    private String summaryStatus;

    private String reportLink;
    private String comments;

    // Getters and Setters
}
