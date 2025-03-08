// src/main/java/com/testmanagement/model/Report.java
package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "reports")
public class Report {
    @Id
    private String id;
    private String projectName;
    private String testCaseReport; // Can be a file path or URL
    private Date date;
}
