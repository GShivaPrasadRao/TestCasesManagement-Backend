package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "analytics")
public class Analytics {
    @Id
    private String id;
    private String projectName;
    private String testCase;
    private String status;
    private Date executionDate;
    private String executedBy;
    private String comments;
}
