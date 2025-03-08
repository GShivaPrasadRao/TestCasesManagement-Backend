package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.Report;
import com.buildtechknowledge.spring.data.mongodb.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(String id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(String id, Report updatedReport) {
        return reportRepository.findById(id)
                .map(report -> {
                    report.setProjectName(updatedReport.getProjectName());
                    report.setTestCaseReport(updatedReport.getTestCaseReport());
                    report.setDate(updatedReport.getDate());
                    return reportRepository.save(report);
                }).orElseGet(() -> {
                    updatedReport.setId(id);
                    return reportRepository.save(updatedReport);
                });
    }

    public void deleteReport(String id) {
        reportRepository.deleteById(id);
    }
}
