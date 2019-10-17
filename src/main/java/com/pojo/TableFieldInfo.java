package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableFieldInfo {
    private String name;
    private String typeName;
    private Class typeClass;
    private String comment;
}
