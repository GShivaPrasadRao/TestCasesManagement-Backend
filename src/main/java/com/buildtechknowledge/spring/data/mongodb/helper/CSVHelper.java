package com.buildtechknowledge.spring.data.mongodb.helper;

import com.buildtechknowledge.spring.data.mongodb.model.Tutorial;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    private CSVHelper()
    {

    }
    public static final String TYPE = "text/csv";

    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Tutorial> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Tutorial tutorial = new Tutorial(
//                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Id"),
                        csvRecord.get("Title"),
                        csvRecord.get("Description"),
                        Boolean.parseBoolean(csvRecord.get("Published"))
                );
                tutorials.add(tutorial);
            }
            return tutorials;
        } catch (IOException e) {
            throw new IllegalArgumentException("fail to parse CSV file: " + e.getMessage());
        }
    }
}