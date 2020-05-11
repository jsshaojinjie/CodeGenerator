package com.generator;

import com.config.GlobalConfig;
import com.util.Util;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class ControllerGenerator {



    private static AnnotationSpec requestBodyAnnotation = AnnotationSpec.builder(org.springframework.web.bind.annotation.RequestBody.class).build();
    private static AnnotationSpec validAnnotation = AnnotationSpec.builder(javax.validation.Valid.class).build();
    private static AnnotationSpec pathVariableAnnotation = AnnotationSpec.builder(org.springframework.web.bind.annotation.PathVariable.class).build();
    private GlobalConfig globalConfig;
    public ControllerGenerator(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public void generator() throws IOException {


        TypeSpec typeSpec = TypeSpec.classBuilder(globalConfig.modelName + "Controller")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(org.springframework.web.bind.annotation.RestController.class)
                .addAnnotation(AnnotationSpec.builder(org.springframework.web.bind.annotation.RequestMapping.class)
                        .addMember("value", "$S", Util.toLowerCaseFirstOne(globalConfig.modelName))
                        .build())
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.Api.class)
                        .addMember("tag", "$S", globalConfig.modelDescription + "模块")
                        .build())
                .addAnnotation(lombok.AllArgsConstructor.class)
                .addField(globalConfig.serviceClass, globalConfig.serviceImplFieldName, Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(getEditMethod())
                .addMethod(getListPagingMethod())
                .addMethod(getGetEditInfoMethod())
                .addMethod(getGetDetailMethod())
                .addMethod(getDeleteMethod())
                .build();


        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.packagePathStr + ".controller", typeSpec)
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
        ParameterSpec parameterSpec = ParameterSpec.builder(globalConfig.editFormNameClass, "form").addAnnotation(validAnnotation).addAnnotation(requestBodyAnnotation).build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("edit")
                .addModifiers(Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec)
                .addAnnotation(AnnotationSpec.builder(org.springframework.web.bind.annotation.PostMapping.class)
                        .addMember("value", "$S", "edit")
                        .build())
                .addAnnotation(AnnotationSpec.builder(globalConfig.sysLogClass)
                        .addMember("value", "$S", "新增与编辑" + globalConfig.modelDescription)
                        .build())
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiOperation.class)
                        .addMember("value", "$S", "新增与编辑" + globalConfig.modelDescription)
                        .build())
                .addJavadoc("@param form " + globalConfig.modelDescription + "编辑信息\n" +
                        "@return R\n" +
                        "@description 新增与编辑" + globalConfig.modelDescription + "信息\n" +
                        "@auther: 邵锦杰\n")
                .addStatement("$T user = $T.getUser()", globalConfig.chemicalUserClass, globalConfig.securityUtilsClass)
                .addStatement("return this." + globalConfig.serviceImplFieldName + ".edit(form, user)")
                .build();
        return methodSpec;
    }


    public MethodSpec getListPagingMethod() {
        ParameterSpec parameterSpec = ParameterSpec.builder(globalConfig.listPagingFormClass, "form").addAnnotation(validAnnotation).addAnnotation(requestBodyAnnotation).build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("listPaging")
                .addModifiers(Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec)
                .addAnnotation(AnnotationSpec.builder(org.springframework.web.bind.annotation.PostMapping.class)
                        .addMember("value", "$S", "listPaging")
                        .build())
                .addAnnotation(AnnotationSpec.builder(globalConfig.sysLogClass)
                        .addMember("value", "$S", "分页获取" + globalConfig.modelDescription)
                        .build())
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiOperation.class)
                        .addMember("value", "$S", "分页获取" + globalConfig.modelDescription)
                        .build())
                .addJavadoc("@param form " + globalConfig.modelDescription + "分页查询信息\n" +
                        "@return R\n" +
                        "@description 分页获取" + globalConfig.modelDescription + "信息\n" +
                        "@auther: 邵锦杰\n")
                .addStatement("$T user = $T.getUser()", globalConfig.chemicalUserClass, globalConfig.securityUtilsClass)
                .addStatement("return this." + globalConfig.serviceImplFieldName + ".listPaging(form, user)")
                .build();
        return methodSpec;
    }


    public MethodSpec getGetEditInfoMethod() {
        ParameterSpec parameterSpec = ParameterSpec.builder(Integer.class, "id")
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiParam.class)
                        .addMember("name", "$S", "id")
                        .addMember("required", "$L", true)
                        .addMember("value", "$S", globalConfig.modelDescription + "id")
                        .build())
                .addAnnotation(pathVariableAnnotation)
                .build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getEditInfo")
                .addModifiers(Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec)
                .addAnnotation(AnnotationSpec.builder(org.springframework.web.bind.annotation.GetMapping.class)
                        .addMember("value", "$S", "getEditInfo/{id}")
                        .build())
                .addAnnotation(AnnotationSpec.builder(globalConfig.sysLogClass)
                        .addMember("value", "$S", "获取" + globalConfig.modelDescription + "编辑信息")
                        .build())
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiOperation.class)
                        .addMember("value", "$S", "获取" + globalConfig.modelDescription + "编辑信息")
                        .build())
                .addJavadoc("@param id " + globalConfig.modelDescription + "id\n" +
                        "@return R\n" +
                        "@description 获取" + globalConfig.modelDescription + "编辑信息\n" +
                        "@auther: 邵锦杰\n")
                .addStatement("$T user = $T.getUser()", globalConfig.chemicalUserClass, globalConfig.securityUtilsClass)
                .addStatement("return this." + globalConfig.serviceImplFieldName + ".getEditInfo(id, user)")
                .build();
        return methodSpec;
    }


    public MethodSpec getGetDetailMethod() {
        ParameterSpec parameterSpec = ParameterSpec.builder(Integer.class, "id")
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiParam.class)
                        .addMember("name", "$S", "id")
                        .addMember("required", "$L", true)
                        .addMember("value", "$S", globalConfig.modelDescription + "id")
                        .build())
                .addAnnotation(pathVariableAnnotation)
                .build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getDetail")
                .addModifiers(Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec)
                .addAnnotation(AnnotationSpec.builder(org.springframework.web.bind.annotation.GetMapping.class)
                        .addMember("value", "$S", "getDetail/{id}")
                        .build())
                .addAnnotation(AnnotationSpec.builder(globalConfig.sysLogClass)
                        .addMember("value", "$S", "获取" + globalConfig.modelDescription + "详细信息")
                        .build())
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiOperation.class)
                        .addMember("value", "$S", "获取" + globalConfig.modelDescription + "详细信息")
                        .build())
                .addJavadoc("@param id " + globalConfig.modelDescription + "id\n" +
                        "@return R\n" +
                        "@description 获取" + globalConfig.modelDescription + "详细信息\n" +
                        "@auther: 邵锦杰\n")
                .addStatement("$T user = $T.getUser()", globalConfig.chemicalUserClass, globalConfig.securityUtilsClass)
                .addStatement("return this." + globalConfig.serviceImplFieldName + ".getDetail(id, user)")
                .build();
        return methodSpec;
    }


    public MethodSpec getDeleteMethod() {
        ParameterSpec parameterSpec = ParameterSpec.builder(globalConfig.simpleDeleteFormClass, "form").addAnnotation(validAnnotation).addAnnotation(requestBodyAnnotation).build();
//        ParameterSpec parameterSpec = ParameterSpec.builder(java.util.List.class, "idList")
//                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiParam.class)
//                        .addMember("name", "$S", "idList")
//                        .addMember("required", "$L", true)
//                        .addMember("value", "$S", modelDescription + "id列表")
//                        .build())
//                .addAnnotation(pathVariableAnnotation)
//                .build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("delete")
                .addModifiers(Modifier.PUBLIC)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec)
                .addAnnotation(AnnotationSpec.builder(org.springframework.web.bind.annotation.PostMapping.class)
                        .addMember("value", "$S", "delete")
                        .build())
                .addAnnotation(AnnotationSpec.builder(globalConfig.sysLogClass)
                        .addMember("value", "$S", globalConfig.modelDescription + "删除")
                        .build())
                .addAnnotation(AnnotationSpec.builder(io.swagger.annotations.ApiOperation.class)
                        .addMember("value", "$S", globalConfig.modelDescription + "删除")
                        .build())
                .addJavadoc("@param form " + globalConfig.modelDescription + "主键列表\n" +
                        "@return R\n" +
                        "@description " + globalConfig.modelDescription + "删除\n" +
                        "@auther: 邵锦杰\n")
                .addStatement("$T user = $T.getUser()", globalConfig.chemicalUserClass, globalConfig.securityUtilsClass)
                .addStatement("return this." + globalConfig.serviceImplFieldName + ".delete(form.getIdList(), user)")
                .build();
        return methodSpec;
    }
}

