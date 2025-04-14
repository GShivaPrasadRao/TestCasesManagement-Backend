package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.exception.DuplicateResourceException;
import com.buildtechknowledge.spring.data.mongodb.exception.GenericServiceException;
import com.buildtechknowledge.spring.data.mongodb.exception.InvalidInputException;
import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.message.ResponseMessage;
import com.buildtechknowledge.spring.data.mongodb.model.Project;
import com.buildtechknowledge.spring.data.mongodb.repository.ProjectRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ProjectRepository projectRepository;

    //Get all Projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get project by MongoDB _id (if needed)
    public Optional<Project> getProjectById(String projectID) {
        return projectRepository.findById(projectID);
    }

    // Get project by custom projectID
    public Project getProjectByProjectID(String projectID) {
        return projectRepository.findByProjectID(projectID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with projectID: " + projectID));
    }

   //Create Project
    public ResponseMessage createProject(Project project) {
        try {
            // Validate project name
            if (project.getProjectName() == null || project.getProjectName().trim().isEmpty()) {
                throw new InvalidInputException("Project name cannot be empty or null.");
            }
            // Check if the length exceeds the limit
            int projectNameLength = project.getProjectName().trim().length();
            if (projectNameLength<3 || projectNameLength > 50) {
                throw new InvalidInputException("Project name must be between 3 and 50 characters.");
            }

            // Validate project name format (only letters, numbers, and spaces)
            if (!project.getProjectName().trim().matches("^[a-zA-Z][a-zA-Z0-9 ]*$")) {
                throw new InvalidInputException("Project name must start with a letter and contain only letters, numbers, and spaces.");
            }

            // Generate an ID if none provided
            if (project.getProjectID() == null || project.getProjectID().trim().isEmpty()) {
                String generatedID = sequenceGeneratorService.generateID("project_sequence", "PROJECT");
                project.setProjectID(generatedID);
            }

            // Trim spaces and normalize project name (ignore case)
            String normalizedProjectName = project.getProjectName().trim().toLowerCase();

            // Check for duplicate project name
            if (projectRepository.existsByProjectName(project.getProjectName().trim()) || projectRepository.existsByProjectName(normalizedProjectName)) {
                throw new DuplicateResourceException(
                        String.format("Project name '%s' already exists. Please provide a unique value.", project.getProjectName()));
            }

//            // Set the trimmed name back to the object before saving
//            project.setProjectName(project.getProjectName().trim());

                // Save the project
            projectRepository.save(project);
            return new ResponseMessage("Project created successfully");

        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException(
                    String.format("Project name '%s' already exists. Please provide a unique value.", project.getProjectName()));

        }
        catch (DuplicateResourceException ex) {
            // Re-throw custom exception so it gets handled properly
            throw ex;
        }
        catch (ConstraintViolationException ex) {
            throw new InvalidInputException("Validation failed: " + ex.getMessage());

            } catch (Exception ex) {
            if (ex instanceof InvalidInputException) {
                throw ex; // Ensure InvalidInputException is not overridden
            }
            throw new GenericServiceException("An unexpected error occurred while creating the project.");
        }
    }

    /*
    //Create Project
    public ResponseMessage createProject(Project project) {
        try {
            // Trim project name
            String trimmedProjectName = project.getProjectName() != null ? project.getProjectName().trim() : "";

            // Validate if project name is empty after trimming
            if (trimmedProjectName.isEmpty()) {
                throw new InvalidInputException("Project name cannot be empty or just spaces.");
            }

            // Validate project name length
            if (trimmedProjectName.length() < 3 || trimmedProjectName.length() > 50) {
                throw new InvalidInputException("Project name must be between 3 and 50 characters.");
            }

            // Validate project name format
            if (!trimmedProjectName.matches("^[a-zA-Z][a-zA-Z0-9 ]*$")) {
                throw new InvalidInputException("Project name must start with a letter and contain only letters, numbers, and spaces.");
            }

            // Convert to lowercase to prevent case-sensitive duplicates
            String normalizedProjectName = trimmedProjectName.toLowerCase();

            // Check for duplicates (case-insensitive and trimmed)
            if (projectRepository.existsByNormalizedProjectName(normalizedProjectName)) {
                throw new DuplicateResourceException(
                        String.format("Project name '%s' already exists. Please provide a unique value.", trimmedProjectName));
            }

            // Assign the cleaned project name before saving
            project.setProjectName(trimmedProjectName);

            // Generate an ID if not provided
            if (project.getProjectID() == null || project.getProjectID().trim().isEmpty()) {
                String generatedID = sequenceGeneratorService.generateProjectID("project_sequence", "PROJECT");
                project.setProjectID(generatedID);
            }

            // Save project
            projectRepository.save(project);
            return new ResponseMessage("Project created successfully");

        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException(
                    String.format("Project name '%s' already exists. Please provide a unique value.", project.getProjectName()));

        } catch (ConstraintViolationException ex) {
            throw new InvalidInputException("Validation failed: " + ex.getMessage());

        } catch (Exception ex) {
            throw new GenericServiceException("An unexpected error occurred while creating the project.");
        }
    }

     */


    // Update using custom projectID
    public Project updateProjectByProjectID(String projectID, Project updatedProject) {
        return projectRepository.findByProjectID(projectID)
                .map(project -> {
                    project.setProjectName(updatedProject.getProjectName());
                    project.setProjectDescription(updatedProject.getProjectDescription());
                    // You can update other fields as needed
                    return projectRepository.save(project);
                }).orElseThrow(() -> new ResourceNotFoundException("Project not found with projectID: " + projectID));
    }

    // Delete using custom projectID
    public void deleteProjectByProjectID(String projectID) {
        Project project = projectRepository.findByProjectID(projectID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with projectID: " + projectID));
        projectRepository.delete(project);
    }


    //Delete all Projects
    public void deleteAll() {
        projectRepository.deleteAll();
    }
}
