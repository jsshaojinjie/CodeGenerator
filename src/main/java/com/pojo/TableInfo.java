package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableInfo {
    private String tableName;
    private List<TableFieldInfo> fieldInfoList;
    private String comment;
}
