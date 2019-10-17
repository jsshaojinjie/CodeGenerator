package com.generator;

import com.config.GlobalConfig;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class InterfaceImplGenerator {
    private GlobalConfig globalConfig;

    public InterfaceImplGenerator(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public void generator() throws IOException {


        TypeSpec typeSpec = TypeSpec.classBuilder(globalConfig.serviceImplName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(globalConfig.mbServiceImplClass, globalConfig.mapperClass, globalConfig.entityClass))
                .addAnnotation(lombok.AllArgsConstructor.class)
                .addAnnotation(org.springframework.stereotype.Service.class)
                .addSuperinterface(globalConfig.serviceImplClass)
                .addMethod(getEditMethod())
                .addMethod(getListPagingMethod())
                .addMethod(getGetEditInfoMethod())
                .addMethod(getGetDetailMethod())
                .addMethod(getDeleteMethod())
                .build();


        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(globalConfig.serviceImplPathStr, typeSpec)
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
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement(globalConfig.modelName + " " + globalConfig.entityName + " = new " + globalConfig.modelName + "()")
                .addStatement(" if (form.getId() != null) {\n" +
                        globalConfig.entityName + " = this.getById(form.getId());\n" +
                        "if (" + globalConfig.entityName + " == null) {\n" +
                        "   return R.builder().code(CommonConstants.FAIL).msg(\"不存在此" + globalConfig.modelDescription + "信息\").build();\n" +
                        "}\n" +
                        globalConfig.entityName + ".setUpdateTime(LocalDateTime.now());\n" +
                        "} else {\n" +
                        "   " + globalConfig.entityName + ".setCompanyId(user.getCompanyId());\n" +
                        "   " + globalConfig.entityName + ".setCreateTime(LocalDateTime.now());\n" +
                        "   " + globalConfig.entityName + ".setUpdateTime($T.now());\n" +
                        "   " + globalConfig.entityName + ".setEffectiveSign($T.EFFECTIVESIGN);\n" +
                        "}", globalConfig.localDateTimeClass, globalConfig.commonConstantsClass)
                .addStatement("$T.copyProperties(form, " + globalConfig.entityName + ");\n" +
                        "this.saveOrUpdate(" + globalConfig.entityName + ");\n" +
                        "return R.builder().build()", globalConfig.beanUtilsClass)
                .build();
        return methodSpec;
    }


    public MethodSpec getListPagingMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(globalConfig.listPagingFormClass, "form").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("listPaging")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement("form.setCompanyId(user.getCompanyId())")
                .addStatement("$T<$T> page = new Page<$T>(form.getPage().getCurrent(), form.getPage().getSize());\n" +
                        "        List<$T> records = this.baseMapper.selectPageList(page, form);\n" +
                        "        page.setRecords(records);\n" +
                        "        return R.builder().data(page).build()", globalConfig.mbPageClass, globalConfig.pagingInfoClass, globalConfig.pagingInfoClass, globalConfig.pagingInfoClass)
                .build();
        return methodSpec;
    }


    public MethodSpec getGetEditInfoMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(Integer.class, "id").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getEditInfo")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement("  " + globalConfig.modelName + " " + globalConfig.entityName + " = this.getById(id);\n" +
                        "        if (" + globalConfig.entityName + " == null) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"不存在此" + globalConfig.modelDescription + "信息\").build();\n" +
                        "        } else if (!" + globalConfig.entityName + ".getCompanyId().equals(user.getCompanyId())) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"您无权编辑此信息\").build();\n" +
                        "        }\n" +
                        "        $T result = new $T();\n" +
                        "        BeanUtils.copyProperties(" + globalConfig.entityName + ", result);\n" +
                        "        return R.builder().data(result).build()", globalConfig.editInfoClass, globalConfig.editInfoClass)
                .build();
        return methodSpec;
    }


    public MethodSpec getGetDetailMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(Integer.class, "id").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getDetail")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement("  " + globalConfig.modelName + " " + globalConfig.entityName + " = this.getById(id);\n" +
                        "        if (" + globalConfig.entityName + " == null) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"不存在此" + globalConfig.modelDescription + "信息\").build();\n" +
                        "        } else if (!" + globalConfig.entityName + ".getCompanyId().equals(user.getCompanyId())) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"您无权查看此信息\").build();\n" +
                        "        }\n" +
                        "        $T result = new $T();\n" +
                        "        BeanUtils.copyProperties(" + globalConfig.entityName + ", result);\n" +
                        "        return R.builder().data(result).build()", globalConfig.detailClass, globalConfig.detailClass)
                .build();
        return methodSpec;
    }


    public MethodSpec getDeleteMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(java.util.List.class, "idList").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(globalConfig.chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("delete")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(globalConfig.rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement(" if (idList.size() > 0) {\n" +
                        "            this.remove(new $T<$T>().lambda().in($T::getId, idList).eq($T::getCompanyId, user.getCompanyId()));\n" +
                        "        }\n" +
                        "        return R.builder().build()", globalConfig.mbQueryWrapperClass, globalConfig.entityClass, globalConfig.entityClass, globalConfig.entityClass)
                .build();
        return methodSpec;
    }
}

