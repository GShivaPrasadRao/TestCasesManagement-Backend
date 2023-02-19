package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private Date createdDate;

    public TestCase()
    {

    }

    public TestCase(String id, String module, String screenName, String name, String description, String type, String status, String comments,Date createdDate) {
        this.id = id;
        this.module = module;
        this.screenName = screenName;
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.comments = comments;
        this.createdDate = createdDate;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id='" + id + '\'' +
                ", module='" + module + '\'' +
                ", screenName='" + screenName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
