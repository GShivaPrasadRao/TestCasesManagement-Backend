package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.model.Project;
import com.buildtechknowledge.spring.data.mongodb.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Project createProject(Project project) {
        // Generate an ID if none provided
        if (project.getProjectID() == null || project.getProjectID().isEmpty()) {
            String generatedID = sequenceGeneratorService.generateProjectID("project_sequence", "PROJECT");
            project.setProjectID(generatedID);
        }
        return projectRepository.save(project);
    }

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


}
