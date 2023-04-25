package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project,String> {
}
