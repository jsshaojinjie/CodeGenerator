package com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard;

import io.swagger.annotations.ApiModelProperty;
import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommonEmergencyDisposalCardEditForm {
  /**
   * 主键
   */
  @ApiModelProperty("更新时必填字段")
  private Integer id;

  /**
   * 岗位名称
   */
  @ApiModelProperty("岗位名称 长度为100")
  @NotNull(
      message = "岗位名称不能为空"
  )
  private String postName;

  /**
   * 生产安全事故处置程序与职责
   */
  @ApiModelProperty("生产安全事故处置程序与职责 无长度限制")
  @NotNull(
      message = "生产安全事故处置程序与职责不能为空"
  )
  private String disposalProcedureResponsibility;

  /**
   * 注意事项
   */
  @ApiModelProperty("注意事项 无长度限制")
  @NotNull(
      message = "注意事项不能为空"
  )
  private String note;

  /**
   * 本企业救援队伍联系方式
   */
  @ApiModelProperty("本企业救援队伍联系方式 长度为20")
  @NotNull(
      message = "本企业救援队伍联系方式不能为空"
  )
  private String companyRescueContact;

  /**
   * 应急负责人联系方式
   */
  @ApiModelProperty("应急负责人联系方式 长度为20")
  @NotNull(
      message = "应急负责人联系方式不能为空"
  )
  private String emergencyHeaderContact;

  /**
   * 控制室联系方式
   */
  @ApiModelProperty("控制室联系方式 长度为20")
  @NotNull(
      message = "控制室联系方式不能为空"
  )
  private String controlDepatmentContact;

  /**
   * 工厂总经理联系方式
   */
  @ApiModelProperty("工厂总经理联系方式 长度为20")
  @NotNull(
      message = "工厂总经理联系方式不能为空"
  )
  private String companyPresidentContact;

  /**
   * 班长联系方式
   */
  @ApiModelProperty("班长联系方式 长度为20")
  @NotNull(
      message = "班长联系方式不能为空"
  )
  private String monitorContact;

  /**
   * 当地应急响应中心联系方式
   */
  @ApiModelProperty("当地应急响应中心联系方式 长度为20")
  @NotNull(
      message = "当地应急响应中心联系方式不能为空"
  )
  private String localeEmergencyCenterContact;

  /**
   * 当地安监部门联系方式
   */
  @ApiModelProperty("当地安监部门联系方式 长度为20")
  @NotNull(
      message = "当地安监部门联系方式不能为空"
  )
  private String localeSafetySupervisionContact;

  /**
   * 当地环保部门联系方式
   */
  @ApiModelProperty("当地环保部门联系方式 长度为20")
  @NotNull(
      message = "当地环保部门联系方式不能为空"
  )
  private String localeEnvironmentalProtectionContact;

  /**
   * 社会救援队联系方式
   */
  @ApiModelProperty("社会救援队联系方式 长度为20")
  @NotNull(
      message = "社会救援队联系方式不能为空"
  )
  private String socialRescureContact;

  /**
   * 友邻单位名称
   */
  @ApiModelProperty("友邻单位名称 长度为100")
  @NotNull(
      message = "友邻单位名称不能为空"
  )
  private String dissepimentCompanyName;

  /**
   * 公司id
   */
  @ApiModelProperty("公司id")
  @NotNull(
      message = "公司id不能为空"
  )
  private Integer companyId;
}
