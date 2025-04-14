package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "pathLocator")
public class PathLocator {
    @Id
    private String id;
    private String projectName;
    private String projectPage;
    private String keyName;
    private String value;
    private String environment;    // e.g., dev, staging, prod
}
