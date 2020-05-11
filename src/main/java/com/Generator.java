package com;

import com.config.DataSourceConfig;
import com.config.GlobalConfig;
import com.generator.*;
import com.pojo.TableInfo;
import com.squareup.javapoet.ClassName;
import com.util.Util;

import java.io.IOException;
import java.util.concurrent.Executors;

public class Generator {

    public static void main(String[] args) throws Exception {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .builder()
                .url("jdbc:mysql://10.8.1.26:3306/c_chemical?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8")
                .driverName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("root").build();
        String packagePathStr = "com.tdgroup.chemical.locale";
        String[] tableNames = {"common_emergency_disposal_card"};
        for (String tableName : tableNames) {
            GlobalConfig globalConfig = new GlobalConfig(tableName, packagePathStr);
            TableInfo tableInfo = new EntityGenerator(dataSourceConfig, globalConfig).generator();
            new ControllerGenerator(globalConfig).generator();
            new InterfaceGenerator(globalConfig).generator();
            new InterfaceImplGenerator(globalConfig).generator();
            new PojoGenerator(globalConfig, tableInfo).generator();
            new MapperGenerator(globalConfig).generator();
            new MapperResourceGenerator(globalConfig).generator();
        }

    }
}
