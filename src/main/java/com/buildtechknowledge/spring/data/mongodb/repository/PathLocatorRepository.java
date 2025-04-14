package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.PathLocator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PathLocatorRepository extends MongoRepository<PathLocator, String> {
    Optional<PathLocator> findByProjectNameAndProjectPageAndKeyName(String projectName, String projectPage, String keyName);

}
