package com;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

import static com.Generator.*;

public class InterfaceImplGenerator {


    public static void generator() throws IOException {


        TypeSpec typeSpec = TypeSpec.classBuilder(serviceImplName)
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(mbServiceImplClass, mapperClass, entityClass))
                .addAnnotation(lombok.AllArgsConstructor.class)
                .addAnnotation(org.springframework.stereotype.Service.class)
                .addSuperinterface(serviceImplClass)
                .addMethod(getEditMethod())
                .addMethod(getListPagingMethod())
                .addMethod(getGetEditInfoMethod())
                .addMethod(getGetDetailMethod())
                .addMethod(getDeleteMethod())
                .build();


        //生成一个Java文件
        JavaFile javaFile = JavaFile.builder(serviceImplPathStr, typeSpec)
                .build();

        //将java写到当前项目中
        javaFile.writeTo(System.out);    //打印到命令行中
        File file = new File("." + "\\src\\");
        if (file.exists()) {
            file.delete();
        }
        javaFile.writeTo(file);
    }


    public static MethodSpec getEditMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(editFormNameClass, "form").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("edit")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement(modelName + " " + entityName + " = new " + modelName + "()")
                .addStatement(" if (form.getId() != null) {\n" +
                        entityName + " = this.getById(form.getId());\n" +
                        "if (" + entityName + " == null) {\n" +
                        "   return R.builder().code(CommonConstants.FAIL).msg(\"不存在此" + modelDescription + "信息\").build();\n" +
                        "}\n" +
                        entityName + ".setUpdateTime(LocalDateTime.now());\n" +
                        "} else {\n" +
                        "   " + entityName + ".setCompanyId(user.getCompanyId());\n" +
                        "   " + entityName + ".setCreateTime(LocalDateTime.now());\n" +
                        "   " + entityName + ".setUpdateTime(LocalDateTime.now());\n" +
                        "   " + entityName + ".setEffectiveSign(CommonConstants.EFFECTIVESIGN);\n" +
                        "}")
                .addStatement("BeanUtils.copyProperties(form, " + entityName + ");\n" +
                        "this.saveOrUpdate(" + entityName + ");\n" +
                        "return R.builder().build();")
                .build();
        return methodSpec;
    }


    public static MethodSpec getListPagingMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(listPagingFormClass, "form").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("listPaging")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement("form.setCompanyId(user.getCompanyId());")
                .addStatement("Page<$T> page = new Page<$T>(form.getCurrent(), form.getSize());\n" +
                        "        List<$T> records = this.baseMapper.selectPageList(page, form);\n" +
                        "        page.setRecords(records);\n" +
                        "        return R.builder().data(page).build();", pagingInfoClass, pagingInfoClass, pagingInfoClass)
                .build();
        return methodSpec;
    }


    public static MethodSpec getGetEditInfoMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(Integer.class, "id").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getEditInfo")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement("  " + modelName + " " + entityName + " = this.getById(id);\n" +
                        "        if (" + entityName + " == null) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"不存在此" + modelDescription + "信息\").build();\n" +
                        "        } else if (!" + entityName + ".getCompanyId().equals(user.getCompanyId())) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"您无权编辑此信息\").build();\n" +
                        "        }\n" +
                        "        $T result = new $T();\n" +
                        "        BeanUtils.copyProperties(majorHazard, result);\n" +
                        "        return R.builder().data(result).build();", editInfoClass, editInfoClass)
                .build();
        return methodSpec;
    }


    public static MethodSpec getGetDetailMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(Integer.class, "id").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("getDetail")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement("  " + modelName + " " + entityName + " = this.getById(id);\n" +
                        "        if (" + entityName + " == null) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"不存在此" + modelDescription + "信息\").build();\n" +
                        "        } else if (!" + entityName + ".getCompanyId().equals(user.getCompanyId())) {\n" +
                        "            return R.builder().code(CommonConstants.FAIL).msg(\"您无权查看此信息\").build();\n" +
                        "        }\n" +
                        "        $T result = new $T();\n" +
                        "        BeanUtils.copyProperties(majorHazard, result);\n" +
                        "        return R.builder().data(result).build();", detailClass, detailClass)
                .build();
        return methodSpec;
    }


    public static MethodSpec getDeleteMethod() {
        ParameterSpec parameterSpec1 = ParameterSpec.builder(java.util.List.class, "idList").build();
        ParameterSpec parameterSpec2 = ParameterSpec.builder(chemicalUserClass, "user").build();
        MethodSpec methodSpec = MethodSpec.methodBuilder("delete")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(rClass)
                .addParameter(parameterSpec1)
                .addParameter(parameterSpec2)
                .addStatement(" if (idList.size() > 0) {\n" +
                        "            this.remove(new QueryWrapper<$T>().lambda().in($T::getId, idList).eq($T::getCompanyId, user.getCompanyId()));\n" +
                        "        }\n" +
                        "        return R.builder().build();", entityClass, entityClass, entityClass)
                .build();
        return methodSpec;
    }
}

