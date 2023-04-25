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
    private String module;
    private String screenName;
    private String name;
    private String description;
    private String type;
    private String status;
    private String comments;
    private String projectID;
    private Date createdDate;


}
