package com;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class PojoGenerator {

    public static void generator() throws IOException {
        generatorDetailPojo();
        generatorEditFormPojo();
        generatorEditInfoPojo();
        generatorPagingFormPojo();
        generatorPagingInfoPojo();
    }

    public static void generatorDetailPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(detailName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(lombok.Data.class)
                .superclass(entityClass)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public static void generatorEditFormPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(editFormName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(lombok.Data.class)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public static void generatorEditInfoPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(editInfoName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(entityClass)
                .addAnnotation(lombok.Data.class)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public static void generatorPagingFormPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(listPagingFormName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(pageInfoClass)
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiModel.class)
                        .addMember("value", "$S", modelDescription + "分页请求参数")
                        .build())
                .addAnnotation(lombok.Data.class)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(pojoPathStr, typeSpec)
                .build();
        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public static void generatorPagingInfoPojo() throws IOException {
        TypeSpec typeSpec = TypeSpec.classBuilder(pagingInfoName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(entityClass)
                .addAnnotation(lombok.Data.class)
                .build();
        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(pojoPathStr, typeSpec)
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

