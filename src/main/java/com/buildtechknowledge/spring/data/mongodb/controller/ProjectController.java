package com.buildtechknowledge.spring.data.mongodb.controller;

import com.buildtechknowledge.spring.data.mongodb.exception.ResourceNotFoundException;
import com.buildtechknowledge.spring.data.mongodb.model.Project;
import com.buildtechknowledge.spring.data.mongodb.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectRepository projectRepository;

    //Create Project
    @PostMapping("/projects")
    public ResponseEntity<Project> createProjects(@RequestBody Project projectDetails) {
        logger.info("*****Create Project *********");

        try {
            Project project = projectRepository.save(projectDetails);
            return new ResponseEntity<>(project , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //List all Projects
    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        System.out.println("********Get List of Projects ********");
        return projectRepository.findAll();
    }

    //Get Projects  by Id
    @GetMapping("/projects/id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") String id) {
        Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Update Test Case
    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") String id, @RequestBody Project projectDetails) {

        logger.info("*****Update Project by ID*********");
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Project not exist with id :" + id));

        project.setProjectID(projectDetails.getProjectID());
        project.setProjectName(projectDetails.getProjectName());
        project.setProjectDescription(projectDetails.getProjectDescription());

        Project updatedProject = projectRepository.save(project);

        return ResponseEntity.ok(updatedProject);
        //return new ResponseEntity<>(updatedProject , HttpStatus.OK);
    }

    //Delete Project  by ID
    @DeleteMapping("/projects/id/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") String id) {
        try {
            System.out.println("*****Delete Project by ID*********");

            projectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete all Test Cases
    @DeleteMapping("/projects")
    public ResponseEntity<HttpStatus> deleteAllProjects() {
        try {
           logger.info("*****Delete all Projects*********");

            projectRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
