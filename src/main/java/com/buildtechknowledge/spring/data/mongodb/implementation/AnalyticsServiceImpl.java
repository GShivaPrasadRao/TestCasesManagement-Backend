package com.buildtechknowledge.spring.data.mongodb.implementation;

import com.buildtechknowledge.spring.data.mongodb.model.TestResult;
import com.buildtechknowledge.spring.data.mongodb.repository.TestResultRepository;
import com.buildtechknowledge.spring.data.mongodb.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private final TestResultRepository testResultRepository;

    @Override
    public Map<String, Object> getProjectSummary(String projectID) {
        Map<String, Object> summary = new HashMap<>();
        List<TestResult> results = testResultRepository.findByProjectID(projectID);

        long total = results.size();
        long passed = results.stream().filter(r -> r.getStatus().equalsIgnoreCase("Pass")).count();
        long failed = results.stream().filter(r -> r.getStatus().equalsIgnoreCase("Fail")).count();
        long blocked = results.stream().filter(r -> r.getStatus().equalsIgnoreCase("Blocked")).count();
        long skipped = results.stream().filter(r -> r.getStatus().equalsIgnoreCase("Skipped")).count();

        summary.put("total", total);
        summary.put("passed", passed);
        summary.put("failed", failed);
        summary.put("blocked", blocked);
        summary.put("skipped", skipped);

        return summary;
    }

//    @Override
//    public List<Map<String, Object>> getStatusTrend(String projectID) {
//        List<TestResult> results = testResultRepository.findByProjectID(projectID);
//
//        return results.stream()
//                .collect(Collectors.groupingBy(
//                        r -> new SimpleDateFormat("yyyy-MM-dd").format(r.getExecutionDate()),
//                        Collectors.groupingBy(TestResult::getStatus, Collectors.counting())
//                ))
//                .entrySet().stream()
//                .map(entry -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("date", entry.getKey());
//                    map.putAll(entry.getValue());
//                    return map;
//                }).collect(Collectors.toList());
//    }

    @Override
    public List<Map<String, Object>> getStatusTrend(String projectID) {
        List<TestResult> results = testResultRepository.findByProjectID(projectID);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"); // parse ISO string
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); // group by day only

        return results.stream()
                .collect(Collectors.groupingBy(
                        r -> {
                            try {
                                Date parsedDate = inputFormat.parse(r.getExecutionDate());
                                return outputFormat.format(parsedDate); // return only date part
                            } catch (Exception e) {
                                return "invalid-date"; // handle parse errors
                            }
                        },
                        Collectors.groupingBy(TestResult::getStatus, Collectors.counting())
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("date", entry.getKey());
                    map.putAll(entry.getValue());
                    return map;
                }).collect(Collectors.toList());
    }


    @Override
    public List<Map<String, Object>> getTesterWiseStats(String projectID) {
        List<TestResult> results = testResultRepository.findByProjectID(projectID);

        return results.stream()
                .collect(Collectors.groupingBy(
                        TestResult::getExecutedBy,
                        Collectors.groupingBy(TestResult::getStatus, Collectors.counting())
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("executedBy", entry.getKey());
                    map.putAll(entry.getValue());
                    return map;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getEnvironmentWiseStats(String projectID) {
        List<TestResult> results = testResultRepository.findByProjectID(projectID);

        return results.stream()
                .collect(Collectors.groupingBy(
                        TestResult::getEnvironment,
                        Collectors.groupingBy(TestResult::getStatus, Collectors.counting())
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("environment", entry.getKey());
                    map.putAll(entry.getValue());
                    return map;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getFlakyTests(String projectID, double threshold) {
        List<TestResult> results = testResultRepository.findByProjectID(projectID);

        Map<String, List<TestResult>> groupedByTestCase = results.stream()
                .collect(Collectors.groupingBy(TestResult::getTestCaseID));

        List<Map<String, Object>> flakyTests = new ArrayList<>();

        for (Map.Entry<String, List<TestResult>> entry : groupedByTestCase.entrySet()) {
            String testCaseID = entry.getKey();
            List<TestResult> testRuns = entry.getValue();

            long totalRuns = testRuns.size();
            long failures = testRuns.stream()
                    .filter(r -> r.getStatus().equalsIgnoreCase("Fail"))
                    .count();

            double failRate = totalRuns > 0 ? (failures * 100.0 / totalRuns) : 0;

            if (failRate >= threshold) {
                Map<String, Object> flakyInfo = new HashMap<>();
                flakyInfo.put("testCaseID", testCaseID);
                flakyInfo.put("testCaseName", testRuns.get(0).getTestCaseName());
                flakyInfo.put("totalRuns", totalRuns);
                flakyInfo.put("failures", failures);
                flakyInfo.put("failRate", failRate);
                flakyTests.add(flakyInfo);
            }
        }

        return flakyTests;
    }
}
