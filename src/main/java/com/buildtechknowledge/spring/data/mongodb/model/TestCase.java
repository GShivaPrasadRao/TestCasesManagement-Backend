package com.buildtechknowledge.spring.data.mongodb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "testcases")
public class TestCase {
    @Id
    private String id;

    private String projectID;
    private String module;
    private String screenName;
    private String testCaseName;
    private String description;
    private String type;
    private String expectedResult;
    private String automationStatus;
    private String priority;
    private String comments;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private String version;


}
