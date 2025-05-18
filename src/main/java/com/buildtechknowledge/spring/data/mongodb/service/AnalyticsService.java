package com.buildtechknowledge.spring.data.mongodb.service;


import java.util.List;
import java.util.Map;

public interface AnalyticsService {
    Map<String, Object> getProjectSummary(String projectID);

    List<Map<String, Object>> getStatusTrend(String projectID);

    List<Map<String, Object>> getTesterWiseStats(String projectID);

    List<Map<String, Object>> getEnvironmentWiseStats(String projectID);

    List<Map<String, Object>> getFlakyTests(String projectID, double threshold);

}

