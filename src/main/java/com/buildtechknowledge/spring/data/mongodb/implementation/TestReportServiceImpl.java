package com.buildtechknowledge.spring.data.mongodb.implementation;

import com.buildtechknowledge.spring.data.mongodb.model.TestReport;
import com.buildtechknowledge.spring.data.mongodb.repository.TestReportRepository;
import com.buildtechknowledge.spring.data.mongodb.service.TestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestReportServiceImpl implements TestReportService {

    @Autowired
    private TestReportRepository reportRepository;

    @Override
    public TestReport saveReport(TestReport report) {
        return reportRepository.save(report);
    }

    @Override
    public List<TestReport> getReportsByProject(String projectID) {
        return reportRepository.findByProjectID(projectID);
    }

    @Override
    public TestReport getReportById(String id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteReport(String id) {
        reportRepository.deleteById(id);
    }
}
