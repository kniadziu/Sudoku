package pl.edu.agh.mwo.java;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.*;

public class PoiDemo {

    public static void printSheetNames(Workbook wb){
        System.out.println("The given workbook contains the following sheets:");
        for (Sheet sheet : wb) {
            System.out.println(sheet.getSheetName());
        }
    }

    public static void printCellsFromSheet(Sheet sheet){
        System.out.println(String.format("The content of %s sheets:", sheet.getSheetName()));

        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cell.getStringCellValue() + " ");
            }
            System.out.println();
        }
    }

    public static void computePointsForARace(Sheet sheet) {
        System.out.println(String.format("The overall number of points in %s race:", sheet.getSheetName()));
        int sum =0;
        for (Row row : sheet) {
            Cell cell = row.getCell(5);
            if (cell.getCellType().equals(CellType.STRING)) {
                String value = cell.getStringCellValue();
                int intValue = Integer.valueOf(value);
                sum += intValue;
            }
        }
        System.out.println(sum);
    }

    public static void computeAllNumberInAGivenColumn(Sheet sheet, int column) {

        int sum =0;
        for (Row row : sheet) {
            Cell cell = row.getCell(column);
            if (cell.getCellType().equals(CellType.NUMERIC)) {
                sum += (int)cell.getNumericCellValue();
            }
        }
        System.out.println(String.format("The sum of all numbers in column %d is %d", column, sum));
    }
}
