package com.generator;

import com.config.DataSourceConfig;
import com.config.GlobalConfig;
import com.pojo.TableFieldInfo;
import com.pojo.TableInfo;
import com.squareup.javapoet.*;
import com.util.Util;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class PojoGenerator {
    private TableInfo tableInfo;
    private GlobalConfig globalConfig;

    public PojoGenerator(GlobalConfig globalConfig, TableInfo tableInfo) {
        this.tableInfo = tableInfo;
        this.globalConfig = globalConfig;
    }

    public void generator() throws IOException {
        generatorDetailPojo();
        generatorEditFormPojo();
        generatorEditInfoPojo();
        generatorPagingFormPojo();
        generatorPagingInfoPojo();
    }

    public void generatorDetailPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(globalConfig.detailName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(lombok.Data.class)
                .superclass(globalConfig.entityClass)
                .addField(java.lang.Integer.class, "id", Modifier.PRIVATE)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public void generatorEditFormPojo() throws IOException {
        TypeSpec.Builder builder = TypeSpec.classBuilder(globalConfig.editFormName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(lombok.Data.class);

        for (TableFieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(fieldInfo.getTypeClass(), Util.lineToHump(fieldInfo.getName()), Modifier.PRIVATE);
            fieldSpecBuilder.addJavadoc(fieldInfo.getComment() + "\n");
            if ("id".equals(fieldInfo.getName())) {
                fieldSpecBuilder.addAnnotation(
                        AnnotationSpec.builder(globalConfig.sgApiModelPropertyClass)
                                .addMember("value", "$S", "更新时必填字段")
                                .build()
                );
            } else if ("effectiveSign".equals(Util.lineToHump(fieldInfo.getName())) || "createTime".equals(Util.lineToHump(fieldInfo.getName())) || "updateTime".equals(Util.lineToHump(fieldInfo.getName()))) {
                continue;
            } else {

                String lengthStr = "";
                if (fieldInfo.getTypeName().equals("varchar")) {
                    lengthStr = " 长度为" + fieldInfo.getColumnSize();
                } else if (fieldInfo.getTypeName().equals("text")) {
                    lengthStr = " 无长度限制";
                }


                fieldSpecBuilder.addAnnotation(
                        AnnotationSpec.builder(globalConfig.sgApiModelPropertyClass)
                                .addMember("value", "$S", fieldInfo.getComment() + lengthStr)
                                .build()
                );
                fieldSpecBuilder.addAnnotation(
                        AnnotationSpec.builder(globalConfig.notNullClass)
                                .addMember("message", "$S", fieldInfo.getComment() + "不能为空")
                                .build()
                );
            }

            builder.addField(fieldSpecBuilder.build());
        }

        TypeSpec typeSpec = builder.build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public void generatorEditInfoPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(globalConfig.editInfoName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(globalConfig.entityClass)
                .addAnnotation(lombok.Data.class)
                .addField(java.lang.Integer.class, "id", Modifier.PRIVATE)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public void generatorPagingFormPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(globalConfig.listPagingFormName)
                .addModifiers(Modifier.PUBLIC)
//                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiModel.class)
//                        .addMember("value", "$S", globalConfig.modelDescription + "分页请求参数")
//                        .build())
                .addAnnotation(lombok.Data.class)
                .addField(java.lang.Integer.class, "companyId", Modifier.PRIVATE)
                .addField(globalConfig.pageInfoClass, "page", Modifier.PRIVATE)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public void generatorPagingInfoPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(globalConfig.pagingInfoName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(globalConfig.entityClass)
                .addAnnotation(lombok.Data.class)
                .addField(java.lang.Integer.class, "id", Modifier.PRIVATE)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.pojoPathStr, typeSpec)
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

