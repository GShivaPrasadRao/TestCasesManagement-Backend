// src/main/java/com/testmanagement/model/ExecutionLog.java
package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "execution_logs")
public class ExecutionLog {
    @Id
    private String id;
    private String projectName;
    private String testCaseId;
    private String status;
    private Date executionStartTime;
    private Date executionEndTime;
    private String executedBy;
    private String logs;
    private String errorStackTrace;
}
