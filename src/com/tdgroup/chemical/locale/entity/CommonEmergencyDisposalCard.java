package com.tdgroup.chemical.locale.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.lang.Integer;
import java.lang.String;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * <p>
 * 应急处置卡
 * </p>
 * @author 邵锦杰
 */
@Data
public class CommonEmergencyDisposalCard extends Model {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(
      value = "id",
      type = IdType.AUTO
  )
  private Integer id;

  /**
   * 岗位名称
   */
  private String postName;

  /**
   * 生产安全事故处置程序与职责
   */
  private String disposalProcedureResponsibility;

  /**
   * 注意事项
   */
  private String note;

  /**
   * 本企业救援队伍联系方式
   */
  private String companyRescueContact;

  /**
   * 应急负责人联系方式
   */
  private String emergencyHeaderContact;

  /**
   * 控制室联系方式
   */
  private String controlDepatmentContact;

  /**
   * 工厂总经理联系方式
   */
  private String companyPresidentContact;

  /**
   * 班长联系方式
   */
  private String monitorContact;

  /**
   * 当地应急响应中心联系方式
   */
  private String localeEmergencyCenterContact;

  /**
   * 当地安监部门联系方式
   */
  private String localeSafetySupervisionContact;

  /**
   * 当地环保部门联系方式
   */
  private String localeEnvironmentalProtectionContact;

  /**
   * 社会救援队联系方式
   */
  private String socialRescureContact;

  /**
   * 友邻单位名称
   */
  private String dissepimentCompanyName;

  /**
   * 公司id
   */
  private Integer companyId;

  /**
   * 创建时间
   */
  private LocalDateTime createTime;

  /**
   * 更新时间
   */
  private LocalDateTime updateTime;

  /**
   * 有效标识，0有效、1失效
   */
  @TableLogic
  private Integer effectiveSign;
}
