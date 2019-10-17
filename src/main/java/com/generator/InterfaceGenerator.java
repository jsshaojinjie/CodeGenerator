package com.generator;

import com.config.GlobalConfig;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class InterfaceGenerator {

    private GlobalConfig globalConfig;

    public InterfaceGenerator(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public void generator() throws IOException {
        TypeSpec typeSpec = TypeSpec.interfaceBuilder(globalConfig.serviceName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(globalConfig.mbIServiceClass, globalConfig.entityClass))
                .addMethod(getEditMethod())
                .addMethod(getListPagingMethod())
                .addMethod(getGetEditInfoMethod())
                .addMethod(getGetDetailMethod())
                .addMethod(getDeleteMethod())
                .build();


        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.servicePathStr, typeSpec)
                .build();

        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public MethodSpec getEditMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(globalConfig.editFormNameClass, "form").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("edit")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }


    public MethodSpec getListPagingMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(globalConfig.listPagingFormClass, "form").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("listPaging")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }


    public MethodSpec getGetEditInfoMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(Integer.class, "id").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getEditInfo")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }


    public MethodSpec getGetDetailMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(Integer.class, "id").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getDetail")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }


    public MethodSpec getDeleteMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(java.util.List.class, "idList").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("delete")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }
}

