package com.buildtechknowledge.spring.data.mongodb.service;

import com.buildtechknowledge.spring.data.mongodb.helper.ExcelHelper;
import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import com.buildtechknowledge.spring.data.mongodb.repository.TestCaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
  @Autowired
  TestCaseRepository repository;

  public void save(MultipartFile file) {
    try {
      List<TestCase> testcases = ExcelHelper.excelToTestCases(file.getInputStream());
      repository.saveAll(testcases);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<TestCase> testcases = repository.findAll();

//    ByteArrayInputStream in = ExcelHelper.testcasesToExcel(testcases);
    return ExcelHelper.testcasesToExcel(testcases);
  }

  public List<TestCase> getAllTestCases() {
    return repository.findAll();
  }
}
