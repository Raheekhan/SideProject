package utility;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DataReader {

    private static Workbook workbook = null;
    private static Sheet sheet = null;
    private static int numberOfRows, numberOfCols;

    public static String[] getDataFromExcelFile(String filePath, int sheetNumber) {
        String path = System.getProperty("user.dir")+ filePath;
        String[] st = readExcel(path, sheetNumber);
        return st;
    }

    public static String[][] getDataFromExcelFile2D(String filePath, int sheetNumber) {
        String path = System.getProperty("user.dir")+ filePath;
        String[][] st = readExcel2D(path, sheetNumber);
        return st;
    }

    private static String[] readExcel(String filePath, int sheetNumber) {
        String[] data = {};
        File file = new File(filePath);

        try {
            FileInputStream fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheetAt(sheetNumber);
        numberOfRows = sheet.getLastRowNum();
        numberOfCols = sheet.getRow(0).getLastCellNum();
        data = new String[numberOfRows + 1];

        for(int i = 1; i < data.length; i++) {
            Row rows = sheet.getRow(i);
            for(int j = 0; j < numberOfCols; j++) {
                Cell cell = rows.getCell(j);
                String cellData = getCellValue(cell);
                data[i] = cellData;
            }
        }
        return data;
    }

    private static String[][] readExcel2D(String filePath, int sheetNumber) {
        String[][] data = {};
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = workbook.getSheetAt(sheetNumber);
        numberOfRows = sheet.getLastRowNum();
        numberOfCols = sheet.getRow(0).getLastCellNum();
        data = new String[numberOfRows + 1][numberOfCols + 1];

        for(int i = 1; i < data.length; i++) {
            Row rows = sheet.getRow(i);
            for(int j = 0; j < numberOfCols; j++) {
                Cell cell = rows.getCell(j);
                String cellData = getCellValue(cell);
                data[i][j] = cellData;
            }
        }
        return data;
    }

    private static String getCellValue(Cell cell) {
        Object value = null;

        int dataType = cell.getCellType();
        switch (dataType) {
            case Cell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
        }
        return value.toString();
    }
}
