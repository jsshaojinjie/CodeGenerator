package com;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.*;
import java.io.File;
import java.io.IOException;

public class Generator {
    public static String modelName = "MajorHazard";
    public static String modelDescription = "重大危险源";
    public static String packagePathStr = "com.tdgroup.chemical.hazardsource";


    public static String pojoPathStr = packagePathStr + ".pojo." + modelName.toLowerCase();
    public static ClassName entityClass = ClassName.get(packagePathStr + ".entity", modelName);
    public static String entityName = Util.toLowerCaseFirstOne(modelName);


    public static String servicePathStr = packagePathStr + ".service";
    public static String serviceImplPathStr = packagePathStr + ".service.impl";
    public static String serviceName = "I" + modelName + "Service";
    public static String serviceImplFieldName = Util.toLowerCaseFirstOne(modelName + "Service");
    public static String serviceImplName = modelName + "ServiceImpl";
    public static ClassName serviceImplClass = ClassName.get(servicePathStr, serviceName);


    public static String mapperName = modelName + "Mapper";
    public static String mapperPathStr = packagePathStr + ".mapper";
    public static ClassName mapperClass = ClassName.get(mapperPathStr, mapperName);
    public static ClassName baseMapperClass = ClassName.get("com.baomidou.mybatisplus.core.mapper", "BaseMapper");


    public static String editFormName = modelName + "EditForm";
    public static ClassName editFormNameClass = ClassName.get(pojoPathStr, editFormName);

    public static String listPagingFormName = modelName + "PagingForm";
    public static ClassName listPagingFormClass = ClassName.get(pojoPathStr, listPagingFormName);

    public static String pagingInfoName = modelName + "PagingInfo";
    public static ClassName pagingInfoClass = ClassName.get(pojoPathStr, pagingInfoName);

    public static String editInfoName = modelName + "EditInfo";
    public static ClassName editInfoClass = ClassName.get(pojoPathStr, editInfoName);

    public static String detailName = modelName + "Detail";
    public static ClassName detailClass = ClassName.get(pojoPathStr, detailName);


    public static String pageInfoName = "PageInfo";
    public static ClassName pageInfoClass = ClassName.get(packagePathStr + ".pojo", pageInfoName);

    public static ClassName rClass = ClassName.get("com.tdgroup.chemical.common.core.util", "R");
    public static ClassName sysLogClass = ClassName.get("com.tdgroup.chemical.common.log.annotation", "SysLog");
    public static ClassName chemicalUserClass = ClassName.get("com.tdgroup.chemical.common.security.service", "ChemicalUser");
    public static ClassName securityUtilsClass = ClassName.get("com.tdgroup.chemical.common.security.util", "SecurityUtils");

    public static ClassName mbIServiceClass = ClassName.get("com.baomidou.mybatisplus.extension.service", "IService");
    public static ClassName mbServiceImplClass = ClassName.get("com.baomidou.mybatisplus.extension.service.impl", "ServiceImpl");
    public static ClassName mbPageClass = ClassName.get("com.baomidou.mybatisplus.extension.plugins.pagination", "Page");

    public static void main(String[] args) throws IOException {
        ControllerGenerator.generator();
        InterfaceGenerator.generator();
        InterfaceImplGenerator.generator();
        PojoGenerator.generator();
        MapperGenerator.generator();
    }
}
