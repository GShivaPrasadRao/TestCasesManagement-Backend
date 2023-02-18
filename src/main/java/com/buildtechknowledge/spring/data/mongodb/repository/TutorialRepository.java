package com.buildtechknowledge.spring.data.mongodb.repository;

import java.util.List;

import com.buildtechknowledge.spring.data.mongodb.model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TutorialRepository extends MongoRepository<Tutorial, String> {
  List<Tutorial> findByPublished(boolean published);
  List<Tutorial> findByTitleContaining(String title);
}
