package com.tdgroup.chemical.riskhidden.pojo.majorhazard;

import com.tdgroup.chemical.riskhidden.pojo.PageInfo;
import io.swagger.annotations.ApiModel;
import java.lang.Integer;
import lombok.Data;

@ApiModel("重大危险源表分页请求参数")
@Data
public class MajorHazardPagingForm {
  private Integer companyId;

  private PageInfo page;
}
