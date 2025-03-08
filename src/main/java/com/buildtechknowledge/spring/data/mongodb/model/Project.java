package com.buildtechknowledge.spring.data.mongodb.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "projects")
public class Project {

    @Id
    private String id; // MongoDB-generated id

    // Custom project identifier (e.g., PROJECT1)
    @NotBlank(message = "Project ID cannot be empty or null")
    @Indexed(unique = true)
    private String projectID;

    @NotBlank(message = "Project Name cannot be empty or null")
    @Indexed(unique = true)
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Project Name must contain only alphabetical characters and spaces, and no numbers or special characters")
    private String projectName;

    @NotBlank(message = "Project Description cannot be empty or null")
    @Indexed(unique = true)
    // This pattern allows letters, spaces, and special characters but no digits.
    @Pattern(regexp = "^(?!.*\\d).+$", message = "Project Description must not contain any numbers")
    private String projectDescription;
}
