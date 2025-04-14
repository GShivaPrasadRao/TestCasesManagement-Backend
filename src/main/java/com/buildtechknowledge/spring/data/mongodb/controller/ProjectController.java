package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.message.ResponseMessage;
import com.buildtechknowledge.spring.data.mongodb.model.Project;
import com.buildtechknowledge.spring.data.mongodb.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //Get all Projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Get project by custom projectID
    @GetMapping("/{projectID}")
    public ResponseEntity<Project> getProjectByProjectID(@Valid @PathVariable String projectID) {
        Project project = projectService.getProjectByProjectID(projectID);
        return ResponseEntity.ok(project);

    }

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {
        ResponseMessage savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @PutMapping("/{projectID}")
    public  ResponseEntity<Project> updateProject(@Valid @PathVariable String projectID, @Valid @RequestBody Project project) {
        Project updatedProject = projectService.updateProjectByProjectID(projectID, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectID}")
    public ResponseEntity<Void> deleteProjectByProjectID(@PathVariable String projectID) {
        projectService.deleteProjectByProjectID(projectID);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Project> deleteProjects()
    {
        projectService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
