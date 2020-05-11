package com.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowNum = 0;
        int col = 0;
        Row row;
        Cell cell;
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        row = sheet.createRow(1);
        row.setRowStyle(cellStyle);
        cell = row.createCell(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("风险点");
        row.createCell(2).setCellValue("危险源");
        row.createCell(3).setCellValue("可能导致的事故类别");
        row.createCell(4).setCellValue("作业条件危险性评估");
        row.createCell(7).setCellValue("风险级别");
        row.createCell(8).setCellValue("控制措施");

        row = sheet.createRow(2);
        row.setRowStyle(cellStyle);
        row.createCell(4).setCellValue("L可能性取值");
        row.createCell(5).setCellValue("E频率程度");
        row.createCell(6).setCellValue("C严重度取值");

        CellRangeAddress region = new CellRangeAddress(1, 2, 0, 0);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 2, 1, 1);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 2, 2, 2);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 2, 3, 3);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 2, 7, 7);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 2, 8, 8);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 1, 4, 6);
        sheet.addMergedRegion(region);

        File file = new File("C:\\Users\\shaoj\\Desktop\\excel测试.xls");
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }
}
