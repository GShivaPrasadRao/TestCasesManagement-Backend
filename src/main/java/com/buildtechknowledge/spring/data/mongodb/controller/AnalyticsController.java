package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    @Autowired
    private final AnalyticsService analyticsService;

    @GetMapping("/project/{projectID}/summary")
    public ResponseEntity<Map<String, Object>> getSummary(@PathVariable String projectID) {
        return ResponseEntity.ok(analyticsService.getProjectSummary(projectID));
    }

    @GetMapping("/project/{projectID}/trend")
    public ResponseEntity<List<Map<String, Object>>> getTrend(@PathVariable String projectID) {
        return ResponseEntity.ok(analyticsService.getStatusTrend(projectID));
    }

    @GetMapping("/project/{projectID}/testers")
    public ResponseEntity<List<Map<String, Object>>> getTesterStats(@PathVariable String projectID) {
        return ResponseEntity.ok(analyticsService.getTesterWiseStats(projectID));
    }

    @GetMapping("/project/{projectID}/environments")
    public ResponseEntity<List<Map<String, Object>>> getEnvStats(@PathVariable String projectID) {
        return ResponseEntity.ok(analyticsService.getEnvironmentWiseStats(projectID));
    }

    @GetMapping("/project/{projectID}/stability")
    public ResponseEntity<List<Map<String, Object>>> getFlakyTests(
            @PathVariable String projectID,
            @RequestParam(defaultValue = "30") double threshold) {
        return ResponseEntity.ok(analyticsService.getFlakyTests(projectID, threshold));
    }


}
