package com.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.config.DataSourceConfig;
import com.config.GlobalConfig;
import com.pojo.TableFieldInfo;
import com.pojo.TableInfo;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.util.DataBaseUtil;
import com.util.Util;
import lombok.Data;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import static com.Generator.*;


@Data
public class EntityGenerator {

    private DataSourceConfig dataSourceConfig;
    private GlobalConfig globalConfig;

    public EntityGenerator(DataSourceConfig dataSourceConfig, GlobalConfig globalConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.globalConfig = globalConfig;
    }

    public TableInfo generator() {
        Connection conn = DataBaseUtil.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword(), dataSourceConfig.getDriverName());
        if (conn != null) {
            TableInfo tableInfo = DataBaseUtil.getTableInfo(conn, globalConfig.getTableName());
            globalConfig.setModelDescription(tableInfo.getComment());
            DataBaseUtil.closeConnection(conn);
            try {
                this.generatorEntity(tableInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tableInfo;
        }
        return null;
    }

    private void generatorEntity(TableInfo tableInfo) throws IOException {

        FieldSpec serialVersionUIDFieldSpec = FieldSpec.builder(long.class, "serialVersionUID", Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC).initializer("$L", "1L").build();

        TypeSpec.Builder builder = TypeSpec.classBuilder(globalConfig.getModelName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(lombok.Data.class)
                .superclass(globalConfig.mbModelClass)
                .addField(serialVersionUIDFieldSpec)
                .addJavadoc("<p>\n" +
                        "" + tableInfo.getComment() + "\n" +
                        "</p>\n" +
                        "@author 邵锦杰\n"
                );
        for (TableFieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(fieldInfo.getTypeClass(), Util.lineToHump(fieldInfo.getName()), Modifier.PRIVATE);
            fieldSpecBuilder.addJavadoc(fieldInfo.getComment() + "\n");
            if ("id".equals(fieldInfo.getName())) {
                fieldSpecBuilder.addAnnotation(
                        AnnotationSpec.builder(globalConfig.mbTableIdClass)
                                .addMember("value", "$S", "id")
                                .addMember("type", "$T.$L", globalConfig.mbTableIdTypeClass, "AUTO")
                                .build()
                );
            } else if ("effectiveSign".equals(Util.lineToHump(fieldInfo.getName()))) {
                fieldSpecBuilder.addAnnotation(
                        AnnotationSpec.builder(globalConfig.mbTableLogicClass)
                                .build()
                );
            }

            builder.addField(fieldSpecBuilder.build());
        }
        TypeSpec typeSpec = builder.build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.getEntityPathStr(), typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


}

