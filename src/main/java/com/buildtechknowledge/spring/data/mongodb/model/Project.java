package com.buildtechknowledge.spring.data.mongodb.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "projects")
public class Project {

    @Id
    private String id;
    private String projectID;
    private String projectName;
    private String projectDescription;
}
