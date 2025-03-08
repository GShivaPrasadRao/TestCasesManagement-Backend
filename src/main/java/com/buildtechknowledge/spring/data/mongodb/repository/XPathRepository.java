package com.buildtechknowledge.spring.data.mongodb.repository;

import com.buildtechknowledge.spring.data.mongodb.model.XPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XPathRepository extends MongoRepository<XPath, String> {
    Optional<XPath> findByProjectNameAndProjectPageAndKeyName(String projectName, String projectPage, String keyName);

}
