package com.buildtechknowledge.spring.data.mongodb.controller;

import com.buildtechknowledge.spring.data.mongodb.model.TestReport;
import com.buildtechknowledge.spring.data.mongodb.service.TestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class TestReportController {

    @Autowired
    private TestReportService reportService;

    @PostMapping
    public ResponseEntity<TestReport> createReport(@RequestBody TestReport report) {
        return ResponseEntity.ok(reportService.saveReport(report));
    }

    @GetMapping("/project/{projectID}")
    public ResponseEntity<List<TestReport>> getReportsByProject(@PathVariable String projectID) {
        return ResponseEntity.ok(reportService.getReportsByProject(projectID));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestReport> getReportById(@PathVariable String id) {
        TestReport report = reportService.getReportById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
}


