package com.tdgroup.chemical.riskhidden.pojo.majorhazard;

import io.swagger.annotations.ApiModelProperty;
import java.lang.Integer;
import java.lang.String;
import lombok.Data;

@Data
public class MajorHazardEditForm {
  /**
   * 主键
   */
  @ApiModelProperty("更新时必填字段")
  private Integer id;

  /**
   * 重大危险源编号
   */
  @ApiModelProperty("重大危险源编号")
  @ApiModelProperty("重大危险源编号不能为空")
  private String majorHazardCode;

  /**
   * 重大危险源名称
   */
  @ApiModelProperty("重大危险源名称")
  @ApiModelProperty("重大危险源名称不能为空")
  private String majorHazardName;

  /**
   * 类型 1 生产单元重大危险源 2存储单元重大危险源 
   */
  @ApiModelProperty("类型 1 生产单元重大危险源 2存储单元重大危险源 ")
  @ApiModelProperty("类型 1 生产单元重大危险源 2存储单元重大危险源 不能为空")
  private Integer majorHazardType;

  /**
   * 等级
   */
  @ApiModelProperty("等级")
  @ApiModelProperty("等级不能为空")
  private Integer grade;

  /**
   * 描述
   */
  @ApiModelProperty("描述")
  @ApiModelProperty("描述不能为空")
  private String remark;

  /**
   * 生产经营活动类型 1生产 2经营 3使用 4 存储 
   */
  @ApiModelProperty("生产经营活动类型 1生产 2经营 3使用 4 存储 ")
  @ApiModelProperty("生产经营活动类型 1生产 2经营 3使用 4 存储 不能为空")
  private Integer typeOfProductionAndBusinessActivitie;

  /**
   * 生产存储场所产权 1自由 2租赁 
   */
  @ApiModelProperty("生产存储场所产权 1自由 2租赁 ")
  @ApiModelProperty("生产存储场所产权 1自由 2租赁 不能为空")
  private Integer propertyRightsOfProductionAndStorageSites;

  /**
   * 防雷防静电设施是否定期检测 0是 1不是
   */
  @ApiModelProperty("防雷防静电设施是否定期检测 0是 1不是")
  @ApiModelProperty("防雷防静电设施是否定期检测 0是 1不是不能为空")
  private Integer regularTesting;

  /**
   * 控制措施
   */
  @ApiModelProperty("控制措施")
  @ApiModelProperty("控制措施不能为空")
  private String controlMeasuresFile;

  /**
   * 应急预案主键
   */
  @ApiModelProperty("应急预案主键")
  @ApiModelProperty("应急预案主键不能为空")
  private Integer emergencyPlan;

  /**
   * 公司主键
   */
  @ApiModelProperty("公司主键")
  @ApiModelProperty("公司主键不能为空")
  private Integer companyId;
}
