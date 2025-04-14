package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.PathLocator;
import com.buildtechknowledge.spring.data.mongodb.repository.PathLocatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PathLocatorService {

    @Autowired
    private PathLocatorRepository PathLocatorRepository;

    public List<PathLocator> getAllPathLocators() {
        return PathLocatorRepository.findAll();
    }

    public Optional<PathLocator> getPathLocatorById(String id) {
        return PathLocatorRepository.findById(id);
    }

    public PathLocator createPathLocator(PathLocator PathLocator) {
        return PathLocatorRepository.save(PathLocator);
    }

    public PathLocator updatePathLocator(String id, PathLocator updatedPathLocator) {
        return PathLocatorRepository.findById(id)
                .map(PathLocator -> {
                    PathLocator.setProjectName(updatedPathLocator.getProjectName());
                    PathLocator.setProjectPage(updatedPathLocator.getProjectPage());
                    PathLocator.setKeyName(updatedPathLocator.getKeyName());
                    PathLocator.setValue(updatedPathLocator.getValue());
                    PathLocator.setEnvironment(updatedPathLocator.getEnvironment());
                    return PathLocatorRepository.save(PathLocator);
                }).orElseGet(() -> {
                    updatedPathLocator.setId(id);
                    return PathLocatorRepository.save(updatedPathLocator);
                });
    }

    public void deletePathLocator(String id) {
        PathLocatorRepository.deleteById(id);
    }
}
