package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.Analytics;
import com.buildtechknowledge.spring.data.mongodb.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public List<Analytics> getAllAnalytics() {
        return analyticsRepository.findAll();
    }

    public Optional<Analytics> getAnalyticsById(String id) {
        return analyticsRepository.findById(id);
    }

    public Analytics createAnalytics(Analytics analytics) {
        return analyticsRepository.save(analytics);
    }

    public Analytics updateAnalytics(String id, Analytics updatedAnalytics) {
        return analyticsRepository.findById(id)
                .map(analytics -> {
                    analytics.setProjectName(updatedAnalytics.getProjectName());
                    analytics.setTestCase(updatedAnalytics.getTestCase());
                    analytics.setStatus(updatedAnalytics.getStatus());
                    analytics.setExecutionDate(updatedAnalytics.getExecutionDate());
                    analytics.setExecutedBy(updatedAnalytics.getExecutedBy());
                    analytics.setComments(updatedAnalytics.getComments());
                    return analyticsRepository.save(analytics);
                }).orElseGet(() -> {
                    updatedAnalytics.setId(id);
                    return analyticsRepository.save(updatedAnalytics);
                });
    }

    public void deleteAnalytics(String id) {
        analyticsRepository.deleteById(id);
    }
}
