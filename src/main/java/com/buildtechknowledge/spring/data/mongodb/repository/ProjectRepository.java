package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    // Find a project by its custom projectID field
    Optional<Project> findByProjectID(String projectID);

    // Optionally, add a delete method by projectID
    void deleteByProjectID(String projectID);

    boolean existsByProjectName(String projectName);

    @Query("{ 'projectName' : { $regex: ?0, $options: 'i' } }")
    boolean existsByNormalizedProjectName(String projectName);
}
