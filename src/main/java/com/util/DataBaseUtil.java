package com.util;

import com.pojo.TableFieldInfo;
import com.pojo.TableInfo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseUtil {


    public static Connection getConnection(String url, String username, String password, String driverName) {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<TableInfo> getTableInfoList(Connection conn) throws SQLException {
        List<TableInfo> result = new ArrayList<>();
        List<String> tableNameList = getTableNameList(conn);
        for (String name : tableNameList) {
            result.add(getTableInfo(conn, name));
        }
        return result;
    }

    public static List<String> getTableNameList(Connection conn) throws SQLException {
        List<String> result = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }


    public static TableInfo getTableInfo(Connection conn, String tableName) {
        List<TableFieldInfo> fieldInfoList = new ArrayList<>();
        try {
            HashMap<String, String> fieldCommentMap = getTableFieldComment(conn, tableName);
            PreparedStatement pStemt = conn.prepareStatement("select * from " + tableName);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            ResultSet rs = pStemt.executeQuery("show full columns from " + tableName);
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i + 1);
                String typeName = rsmd.getColumnTypeName(i + 1);
                int columnSize = rsmd.getColumnDisplaySize(i + 1);
                int isNullable = rsmd.isNullable(i + 1);
                Class typeClass = null;
                switch (typeName) {
                    case "INT": {
                        typeClass = Integer.class;
                        break;
                    }
                    case "VARCHAR": {
                        typeClass = String.class;
                        break;
                    }
                    case "BIT": {
                        typeName = "tinyint";
                        typeClass = Integer.class;
                        break;
                    }
                    case "DATETIME": {
                        typeClass = LocalDateTime.class;
                        break;
                    }
                    case "DATE": {
                        typeClass = LocalDate.class;
                        break;
                    }
                    default: {
                        typeClass = String.class;
                        break;
                    }
                }
                if (typeName.equals("TEXT") || typeName.equals("DATETIME") || typeName.equals("DATE")) {
                    columnSize = 0;
                }

                String comment = fieldCommentMap.containsKey(name) ? fieldCommentMap.get(name) : null;
                comment = comment.replace("\n", "").replace("\r", " ");
                fieldInfoList.add(new TableFieldInfo(name, typeName.toLowerCase(), typeClass, comment, columnSize, isNullable));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String tableComment = getTableComment(conn, tableName);
        return new TableInfo().builder().tableName(tableName).fieldInfoList(fieldInfoList).comment(tableComment).build();
    }


    public static String getTableComment(Connection conn, String tableName) {
        List<TableFieldInfo> fieldInfoList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
//            ResultSet rs = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                if (tableName.equals(rs.getString(1))) {
                    return parse(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }


    public static HashMap<String, String> getTableFieldComment(Connection conn, String tableName) {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                result.put(rs.getString("Field"), rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
