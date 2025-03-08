// src/main/java/com/testmanagement/model/TestData.java
package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "test_data")
public class TestData {
    @Id
    private String id;
    private String projectName;
    private String connectorCategory;
    private String connectorType;
    // Add additional fields as needed
}
