package com.tdgroup.chemical.riskhidden.entity;

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
 * 重大危险源表
 * </p>
 * @author 邵锦杰
 */
@Data
public class MajorHazard extends Model {
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
   * 重大危险源编号
   */
  private String majorHazardCode;

  /**
   * 重大危险源名称
   */
  private String majorHazardName;

  /**
   * 类型 1 生产单元重大危险源 2存储单元重大危险源 
   */
  private Integer majorHazardType;

  /**
   * 等级
   */
  private Integer grade;

  /**
   * 描述
   */
  private String remark;

  /**
   * 生产经营活动类型 1生产 2经营 3使用 4 存储 
   */
  private Integer typeOfProductionAndBusinessActivitie;

  /**
   * 生产存储场所产权 1自由 2租赁 
   */
  private Integer propertyRightsOfProductionAndStorageSites;

  /**
   * 防雷防静电设施是否定期检测 0是 1不是
   */
  private Integer regularTesting;

  /**
   * 控制措施
   */
  private String controlMeasuresFile;

  /**
   * 应急预案主键
   */
  private Integer emergencyPlan;

  /**
   * 公司主键
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
   * 标志位0有效，1无效
   */
  @TableLogic
  private Integer effectiveSign;
}
