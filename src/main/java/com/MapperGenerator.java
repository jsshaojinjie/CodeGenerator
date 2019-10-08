package com;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class MapperGenerator {


    public static void generator() throws IOException {


        TypeSpec typeSpec = TypeSpec.interfaceBuilder(mapperName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(baseMapperClass, entityClass))
                .addMethod(getSelectPageListMethod())
                .build();


        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(mapperPathStr, typeSpec)
                .build();

        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public static MethodSpec getSelectPageListMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(ParameterizedTypeName.get(mbPageClass, pagingInfoClass), "page").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(listPagingFormClass, "form")
                .addAnnotation(AnnotationSpec.builder(org.apache.ibatis.annotations.Param.class)
                        .addMember("value", "$S", "form")
                        .build())
                .build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("selectPageList")
                .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(ClassName.get("java.util", "List"), pagingInfoClass))
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .build();
        return methodSpec;
    }
}

