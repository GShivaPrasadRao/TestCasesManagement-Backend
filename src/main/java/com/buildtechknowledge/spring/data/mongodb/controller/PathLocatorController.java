package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.model.PathLocator;
import com.buildtechknowledge.spring.data.mongodb.service.PathLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/PathLocators")
public class PathLocatorController {

    @Autowired
    private PathLocatorService PathLocatorService;

    @GetMapping
    public List<PathLocator> getAllPathLocators() {
        return PathLocatorService.getAllPathLocators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PathLocator> getPathLocatorById(@PathVariable String id) {
        return PathLocatorService.getPathLocatorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PathLocator createPathLocator(@RequestBody PathLocator PathLocator) {
        return PathLocatorService.createPathLocator(PathLocator);
    }

    @PutMapping("/{id}")
    public PathLocator updatePathLocator(@PathVariable String id, @RequestBody PathLocator PathLocator) {
        return PathLocatorService.updatePathLocator(id, PathLocator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePathLocator(@PathVariable String id) {
        PathLocatorService.deletePathLocator(id);
        return ResponseEntity.noContent().build();
    }
}
