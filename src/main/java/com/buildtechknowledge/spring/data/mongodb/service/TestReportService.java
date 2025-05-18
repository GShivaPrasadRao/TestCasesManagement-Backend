package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.TestReport;

import java.util.List;

public interface TestReportService {

    TestReport saveReport(TestReport report);
    List<TestReport> getReportsByProject(String projectID);
    TestReport getReportById(String id);
    void deleteReport(String id);
}


