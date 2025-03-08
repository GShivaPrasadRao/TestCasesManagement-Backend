// src/main/java/com/testmanagement/model/AppConfig.java
package com.buildtechknowledge.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "app_config")
public class AppConfig {
    @Id
    private String id;
    private String url;
    private String username;
    private String accessToken;
}
