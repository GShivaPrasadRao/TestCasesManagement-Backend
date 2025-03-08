package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.ExecutionLog;
import com.buildtechknowledge.spring.data.mongodb.repository.ExecutionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutionLogService {

    @Autowired
    private ExecutionLogRepository executionLogRepository;

    public List<ExecutionLog> getAllExecutionLogs() {
        return executionLogRepository.findAll();
    }

    public Optional<ExecutionLog> getExecutionLogById(String id) {
        return executionLogRepository.findById(id);
    }

    public ExecutionLog createExecutionLog(ExecutionLog executionLog) {
        return executionLogRepository.save(executionLog);
    }

    public ExecutionLog updateExecutionLog(String id, ExecutionLog updatedLog) {
        return executionLogRepository.findById(id)
                .map(log -> {
                    log.setProjectName(updatedLog.getProjectName());
                    log.setTestCaseId(updatedLog.getTestCaseId());
                    log.setStatus(updatedLog.getStatus());
                    log.setExecutionStartTime(updatedLog.getExecutionStartTime());
                    log.setExecutionEndTime(updatedLog.getExecutionEndTime());
                    log.setExecutedBy(updatedLog.getExecutedBy());
                    log.setLogs(updatedLog.getLogs());
                    log.setErrorStackTrace(updatedLog.getErrorStackTrace());
                    return executionLogRepository.save(log);
                }).orElseGet(() -> {
                    updatedLog.setId(id);
                    return executionLogRepository.save(updatedLog);
                });
    }

    public void deleteExecutionLog(String id) {
        executionLogRepository.deleteById(id);
    }
}
