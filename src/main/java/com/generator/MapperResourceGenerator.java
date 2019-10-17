package com.generator;

import com.config.GlobalConfig;
import com.squareup.javapoet.*;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.lang.model.element.Modifier;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class MapperResourceGenerator {
    private GlobalConfig globalConfig;

    public MapperResourceGenerator(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }


    public void generator() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //step2:获得一个DocumentBuilder
        DocumentBuilder db = factory.newDocumentBuilder();
        //step3:新建一个Document对象
        Document document = db.newDocument();
        document.setXmlStandalone(true);

        DocumentType doctype = document.getImplementation().createDocumentType("doctype", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        Element DOCTYPE = document.createElement("DOCTYPE");

        Element mapper = document.createElement("mapper");
        mapper.setAttribute("namespace", globalConfig.mapperPathStr + "." + globalConfig.mapperName);

        Element selectPageList = document.createElement("select");
        selectPageList.setAttribute("id", "selectPageList");
        selectPageList.setAttribute("resultType", globalConfig.pojoPathStr + "." + globalConfig.pagingInfoName);
        selectPageList.setTextContent("select * from " + globalConfig.tableName + " where effective_sign = '0' order by id desc");

        mapper.appendChild(selectPageList);
        document.appendChild(mapper);

        Source xmlSource = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());

        Result outputTarget = new StreamResult(new File("." + "\\src\\main\\resources\\mapper\\" + globalConfig.mapperName + ".xml"));
        transformer.transform(xmlSource, outputTarget);


    }

}

