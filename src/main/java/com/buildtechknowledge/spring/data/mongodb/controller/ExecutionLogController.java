package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.model.ExecutionLog;
import com.buildtechknowledge.spring.data.mongodb.service.ExecutionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/executionlogs")
public class ExecutionLogController {

    @Autowired
    private ExecutionLogService executionLogService;

    @GetMapping
    public List<ExecutionLog> getAllExecutionLogs() {
        return executionLogService.getAllExecutionLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExecutionLog> getExecutionLogById(@PathVariable String id) {
        return executionLogService.getExecutionLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExecutionLog createExecutionLog(@RequestBody ExecutionLog executionLog) {
        return executionLogService.createExecutionLog(executionLog);
    }

    @PutMapping("/{id}")
    public ExecutionLog updateExecutionLog(@PathVariable String id, @RequestBody ExecutionLog executionLog) {
        return executionLogService.updateExecutionLog(id, executionLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExecutionLog(@PathVariable String id) {
        executionLogService.deleteExecutionLog(id);
        return ResponseEntity.noContent().build();
    }
}
