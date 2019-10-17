package com.generator;

import com.config.GlobalConfig;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class MapperGenerator {
    private GlobalConfig globalConfig;

    public MapperGenerator(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }


    public void generator() throws IOException {


        TypeSpec typeSpec = TypeSpec.interfaceBuilder(globalConfig.mapperName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(globalConfig.baseMapperClass, globalConfig.entityClass))
                .addMethod(getSelectPageListMethod())
                .build();


        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.mapperPathStr, typeSpec)
                .build();

        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public MethodSpec getSelectPageListMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(ParameterizedTypeName.get(globalConfig.mbPageClass, globalConfig.pagingInfoClass), "page").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.listPagingFormClass, "form")
                .addAnnotation(AnnotationSpec.builder(org.apache.ibatis.annotations.Param.class)
                        .addMember("value", "$S", "form")
                        .build())
                .build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("selectPageList")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(ClassName.get("java.util", "List"), globalConfig.pagingInfoClass))
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }
}

