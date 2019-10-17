package com.config;

import com.squareup.javapoet.ClassName;
import com.util.Util;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.util.Util.toUpperCaseFirstOne;

@Data
public class GlobalConfig {


    public String tableName;
    public String modelName;
    public String modelDescription;
    public String packagePathStr;

    public String entityPathStr;
    public String pojoPathStr;
    public ClassName entityClass;
    public String entityName;


    public String servicePathStr;
    public String serviceImplPathStr;
    public String serviceName;
    public String serviceImplFieldName;
    public String serviceImplName;
    public ClassName serviceImplClass;


    public String mapperName;
    public String mapperPathStr;
    public ClassName mapperClass;
    public ClassName baseMapperClass;


    public String editFormName;
    public ClassName editFormNameClass;

    public String listPagingFormName;
    public ClassName listPagingFormClass;

    public String pagingInfoName;
    public ClassName pagingInfoClass;

    public String editInfoName;
    public ClassName editInfoClass;

    public String detailName;
    public ClassName detailClass;


    public String pageInfoName;
    public ClassName pageInfoClass;

    public ClassName simpleDeleteFormClass;
    public ClassName serviceClass;

    public static ClassName rClass = ClassName.get("com.tdgroup.chemical.common.core.util", "R");
    public static ClassName sysLogClass = ClassName.get("com.tdgroup.chemical.common.log.annotation", "SysLog");
    public static ClassName chemicalUserClass = ClassName.get("com.tdgroup.chemical.common.security.service", "ChemicalUser");
    public static ClassName securityUtilsClass = ClassName.get("com.tdgroup.chemical.common.security.util", "SecurityUtils");
    public static ClassName mbIServiceClass = ClassName.get("com.baomidou.mybatisplus.extension.service", "IService");
    public static ClassName mbServiceImplClass = ClassName.get("com.baomidou.mybatisplus.extension.service.impl", "ServiceImpl");
    public static ClassName mbPageClass = ClassName.get("com.baomidou.mybatisplus.extension.plugins.pagination", "Page");
    public static ClassName mbQueryWrapperClass = ClassName.get("com.baomidou.mybatisplus.core.conditions.query", "QueryWrapper");
    public static ClassName mbModelClass = ClassName.get("com.baomidou.mybatisplus.extension.activerecord", "Model");
    public static ClassName mbTableIdClass = ClassName.get("com.baomidou.mybatisplus.annotation", "TableId");
    public static ClassName mbTableLogicClass = ClassName.get("com.baomidou.mybatisplus.annotation", "TableLogic");
    public static ClassName mbTableIdTypeClass = ClassName.get("com.baomidou.mybatisplus.annotation", "IdType");
    public static ClassName commonConstantsClass = ClassName.get("com.tdgroup.chemical.common.core.constant", "CommonConstants");
    public static ClassName localDateTimeClass = ClassName.get("java.time", "LocalDateTime");
    public static ClassName beanUtilsClass = ClassName.get("org.springframework.beans", "BeanUtils");
    public static ClassName sgApiModelPropertyClass = ClassName.get("io.swagger.annotations", "ApiModelProperty");
    public static ClassName notNullClass = ClassName.get("io.swagger.annotations", "ApiModelProperty");


    public GlobalConfig(String tableName) {
        this.tableName = tableName;

        this.modelName = toUpperCaseFirstOne(Util.lineToHump(tableName));
        this.packagePathStr = "com.tdgroup.chemical.riskhidden";

        this.entityPathStr = packagePathStr + ".entity";
        this.pojoPathStr = packagePathStr + ".pojo." + modelName.toLowerCase();
        this.entityClass = ClassName.get(packagePathStr + ".entity", modelName);
        this.entityName = Util.toLowerCaseFirstOne(modelName);


        this.servicePathStr = packagePathStr + ".service";
        this.serviceImplPathStr = packagePathStr + ".service.impl";
        this.serviceName = "I" + modelName + "Service";
        this.serviceImplFieldName = Util.toLowerCaseFirstOne(modelName + "Service");
        this.serviceImplName = modelName + "ServiceImpl";
        this.serviceImplClass = ClassName.get(servicePathStr, serviceName);


        this.mapperName = modelName + "Mapper";
        this.mapperPathStr = packagePathStr + ".mapper";
        this.mapperClass = ClassName.get(mapperPathStr, mapperName);
        this.baseMapperClass = ClassName.get("com.baomidou.mybatisplus.core.mapper", "BaseMapper");


        this.editFormName = modelName + "EditForm";
        this.editFormNameClass = ClassName.get(pojoPathStr, editFormName);

        this.listPagingFormName = modelName + "PagingForm";
        this.listPagingFormClass = ClassName.get(pojoPathStr, listPagingFormName);

        this.pagingInfoName = modelName + "PagingInfo";
        this.pagingInfoClass = ClassName.get(pojoPathStr, pagingInfoName);

        this.editInfoName = modelName + "EditInfo";
        this.editInfoClass = ClassName.get(pojoPathStr, editInfoName);

        this.detailName = modelName + "Detail";
        this.detailClass = ClassName.get(pojoPathStr, detailName);


        this.pageInfoName = "PageInfo";
        this.pageInfoClass = ClassName.get(packagePathStr + ".pojo", pageInfoName);
        this.simpleDeleteFormClass = ClassName.get(packagePathStr + ".pojo", "SimpleDeleteForm");
        this.serviceClass = ClassName.get(servicePathStr, serviceName);
    }


}
