package com.bezkoder.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testcases")
public class TestCase {
    @Id
    private String id;
    private String sprint;
    private String screenName;
    private String description;
    private String type;
    private String status;
    private String comments;

    public TestCase(String id, String sprint, String screenName, String description, String type, String status, String comments) {
        this.id = id;
        this.sprint = sprint;
        this.screenName = screenName;
        this.description = description;
        this.type = type;
        this.status = status;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
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

    @Override
    public String toString() {
        return "TestCase{" +
                "id='" + id + '\'' +
                ", sprint='" + sprint + '\'' +
                ", screenName='" + screenName + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
