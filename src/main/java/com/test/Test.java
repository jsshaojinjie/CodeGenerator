package com.test;

import com.config.DataSourceConfig;
import com.pojo.TableInfo;
import com.util.DataBaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .builder()
                .url("jdbc:mysql://10.8.1.26:3306/c_chemical?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8")
                .driverName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("root").build();
        Connection conn = DataBaseUtil.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword(), dataSourceConfig.getDriverName());
        if (conn != null) {
            String sql = "select id,ST_AsText( geom)  from zone_base_info";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if(rs.getString(2)!=null){
                    String sqlStr = "update zone_base_info set geom =  ST_GeomFromText('"+rs.getString(2)+"') where id = "+rs.getString(1)+" ;";
                    System.out.println(sqlStr);
                }
             }
        }
    }
}
