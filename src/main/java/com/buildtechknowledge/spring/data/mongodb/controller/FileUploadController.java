package com.buildtechknowledge.spring.data.mongodb.controller;

import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/testcases/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, TestCase testCase) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            // read data from workbook and store it in MongoDB
            mongoTemplate.insert(workbook, "excel_files");
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }
}
