package utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

public class DataReader {

    public static Object[][] readExcelData(String path, String sheetName) {
        String[][] testData = null;
        FileInputStream file = null;
        try {
            file = new FileInputStream(path);

            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            int row = sheet.getRows();
            int column = sheet.getColumns();
            testData = new String[row - 1][column];
            int count = 0;

            for (int i = 1; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    Cell cell = sheet.getCell(j, i);
                    testData[count][j] = cell.getContents();
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return testData;
    }
}