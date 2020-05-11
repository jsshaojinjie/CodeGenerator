package com;

import com.config.DataSourceConfig;
import com.config.GlobalConfig;
import com.generator.*;
import com.pojo.TableFieldInfo;
import com.pojo.TableInfo;
import com.util.DataBaseUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExcelExport {
    public static void main(String[] args) throws SQLException, IOException {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .builder()
                .url("jdbc:mysql://10.8.1.26:3306/z_h_chemical?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8")
                .driverName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("root").build();
        Connection conn = DataBaseUtil.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword(), dataSourceConfig.getDriverName());
        if (conn != null) {
            List<TableInfo> tableInfoList = DataBaseUtil.getTableInfoList(conn);
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            int rowNum = 0;
            int col;
            Row row;
            Cell cell;
            for (TableInfo tableInfo : tableInfoList) {
                col = 0;
                row = sheet.createRow(rowNum++);
                row.createCell(col++).setCellValue("表：" + tableInfo.getComment() + "(" + tableInfo.getTableName() + ")");

                rowNum++;
                col = 0;
                row = sheet.createRow(rowNum++);
                row.createCell(col++).setCellValue("序号");
                row.createCell(col++).setCellValue("字段名称");
                row.createCell(col++).setCellValue("字段类型");
                row.createCell(col++).setCellValue("字段描述");
                row.createCell(col++).setCellValue("字段默认值");
                row.createCell(col++).setCellValue("备注");

                for (TableFieldInfo tableFieldInfo : tableInfo.getFieldInfoList()) {
                    col = 0;
                    row = sheet.createRow(rowNum++);
                    row.createCell(col++).setCellValue(tableInfo.getFieldInfoList().indexOf(tableFieldInfo)+1);
                    row.createCell(col++).setCellValue(tableFieldInfo.getName());
                    row.createCell(col++).setCellValue(tableFieldInfo.getTypeName() + "(" + tableFieldInfo.getColumnSize() + ")");
                    row.createCell(col++).setCellValue(tableFieldInfo.getComment());
                    row.createCell(col++).setCellValue(tableFieldInfo.getIsNullable() == 0 ? "非null" : "null");
                    row.createCell(col++).setCellValue("");
                }
                rowNum++;
                rowNum++;
            }

            File file = new File("C:\\Users\\shaoj\\Desktop\\数据库.xls");
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        }

    }
}
