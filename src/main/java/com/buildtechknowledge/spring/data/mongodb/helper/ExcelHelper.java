package com.buildtechknowledge.spring.data.mongodb.helper;

import com.buildtechknowledge.spring.data.mongodb.model.TestCase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    private ExcelHelper() {

    }

    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "Module", "ScreenName", "Name","Description","Type","Status","Comments"};
    static String SHEET = "TestCase";

    public static boolean hasExcelFormat(MultipartFile file) {

        boolean result = TYPE.equals(file.getContentType());
        System.out.println(result);

//    if (!TYPE.equals(file.getContentType())) {
//      return false;
//    }

        return result;
    }

    public static ByteArrayInputStream testcasesToExcel(List<TestCase> testcases) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (TestCase testcase : testcases) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(testcase.getId());
                row.createCell(1).setCellValue(testcase.getModule());
                row.createCell(2).setCellValue(testcase.getScreenName());
                row.createCell(3).setCellValue(testcase.getTestCaseName());
                row.createCell(4).setCellValue(testcase.getDescription());

                row.createCell(5).setCellValue(testcase.getType());
                row.createCell(6).setCellValue(testcase.getAutomationStatus());
                row.createCell(7).setCellValue(testcase.getComments());


            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<TestCase> excelToTestCases(InputStream is) {
        try {


            Workbook workbook = WorkbookFactory.create(is);
            System.out.println(workbook.getNumberOfSheets());
            int nrSheets = workbook.getNumberOfSheets();

            String[] names = new String[nrSheets];
            for ( int i = 0; i < nrSheets; i++ ) {
                names[i] = workbook.getSheetName( i );
                System.out.println(names[i]);
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<TestCase> testcases = new ArrayList<TestCase>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                TestCase testcase = new TestCase();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
//                        case 0:
//                            testcase.setId(currentCell.getStringCellValue());
//                            break;

                        case 0:
                            testcase.setModule(currentCell.getStringCellValue());
                            break;

                        case 1:
                            testcase.setScreenName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            testcase.setTestCaseName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            testcase.setDescription(currentCell.getStringCellValue());
                            break;

                        case 4:
                            testcase.setType(currentCell.getStringCellValue());
                            break;

                        case 5:
                            testcase.setAutomationStatus(currentCell.getStringCellValue());
                            break;

                        case 6:
                            testcase.setComments(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                testcases.add(testcase);
            }

            workbook.close();

            return testcases;
        } catch (IOException e) {
            throw new IllegalArgumentException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
