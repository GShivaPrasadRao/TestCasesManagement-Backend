package com.buildtechknowledge.spring.data.mongodb.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Update;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "testcases")
public class TestCase {

    @Id
    private String id; // MongoDB-generated id

    // Custom project identifier (e.g., TESTCASE1)
    @NotBlank(message = "TestCase ID cannot be empty or null")
    @Indexed(unique = true)
    private String testCaseID;

    // Ensure projectID is required and unique
    @NotNull(message = "Project ID cannot be null")
    @NotBlank(message = "Project ID cannot be blank")
    @Indexed(unique = true)
    private String projectID;

    // Module field should be non-null
    @NotNull(message = "Module cannot be null")
    @NotBlank(message = "Module cannot be blank")
    @Size(max = 100, message = "Module name cannot exceed 100 characters")
    private String module;

    // Screen name with a non-null and special character check
    @NotNull(message = "Screen name cannot be null")
    @NotBlank(message = "Screen name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-\\s]+$", message = "Screen name can only contain letters, numbers, spaces, underscores, and dashes")
    private String screenName;

    // Test case name must be unique and cannot contain only numbers
    @NotNull(message = "Test case name cannot be null")
    @NotBlank(message = "Test case name cannot be blank")
    @Indexed(unique = true)
    @Pattern(regexp = "^(?!\\d+$).+", message = "Test case name cannot be purely numeric")
    private String testCaseName;

    // Other fields with relevant validations
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    // Type of test case (e.g., Postive, Negative)
    @NotNull(message = "Test Type cannot be null")
    @NotBlank(message = "Test Type cannot be blank")
    private String type;

    //Expected Result
    @NotNull(message = "Expected result cannot be null")
    @NotBlank(message = "Expected result cannot be blank")
    private String expectedResult;

    //Automation status
    @NotNull(message = "Status cannot be null")
    @NotBlank(message = "Status result cannot be blank")
    private String automationStatus;

    //Define the Priority (eg: 1 ,2, 3, 4)
    @NotNull(message = "Priority cannot be null")
    private int priority;

    //Comments if any
    @Size(max = 300, message = "Comments cannot exceed 300 characters")
    private String comments;

    //Name of the user who created this
    @NotNull(message = "Created by cannot be null")
    @NotBlank(message = "Created by cannot be blank")
    private String createdBy;

    //Current Date
    @NotNull(message = "Created date cannot be null")
    @CreatedDate
    private LocalDateTime createdDate;


    //Update by username
    private String updatedBy;



    private LocalDateTime updatedDate;

    @NotNull(message = "Version cannot be null")
    private Double version;


}
