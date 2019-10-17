package com.tdgroup.chemical.riskhidden.controller;

import com.tdgroup.chemical.common.core.util.R;
import com.tdgroup.chemical.common.log.annotation.SysLog;
import com.tdgroup.chemical.common.security.service.ChemicalUser;
import com.tdgroup.chemical.common.security.util.SecurityUtils;
import com.tdgroup.chemical.riskhidden.pojo.SimpleDeleteForm;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardEditForm;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardPagingForm;
import com.tdgroup.chemical.riskhidden.service.IMajorHazardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.lang.Integer;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("majorHazard")
@Api(
    description = "重大危险源表模块"
)
@AllArgsConstructor
public class MajorHazardController {
  private final IMajorHazardService majorHazardService;

  /**
   * @param form 重大危险源表编辑信息
   * @return R
   * @description 新增与编辑重大危险源表信息
   * @auther: 邵锦杰
   */
  @PostMapping("edit")
  @SysLog("新增与编辑重大危险源表")
  @ApiOperation("新增与编辑重大危险源表")
  public R edit(@Valid @RequestBody MajorHazardEditForm form) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.majorHazardService.edit(form, user);
  }

  /**
   * @param form 重大危险源表分页查询信息
   * @return R
   * @description 分页获取重大危险源表信息
   * @auther: 邵锦杰
   */
  @PostMapping("listPaging")
  @SysLog("分页获取重大危险源表")
  @ApiOperation("分页获取重大危险源表")
  public R listPaging(@Valid @RequestBody MajorHazardPagingForm form) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.majorHazardService.listPaging(form, user);
  }

  /**
   * @param id 重大危险源表id
   * @return R
   * @description 获取重大危险源表编辑信息
   * @auther: 邵锦杰
   */
  @GetMapping("getEditInfo/{id}")
  @SysLog("获取重大危险源表编辑信息")
  @ApiOperation("获取重大危险源表编辑信息")
  public R getEditInfo(
      @ApiParam(name = "id", required = true, value = "重大危险源表id") @PathVariable Integer id) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.majorHazardService.getEditInfo(id, user);
  }

  /**
   * @param id 重大危险源表id
   * @return R
   * @description 获取重大危险源表详细信息
   * @auther: 邵锦杰
   */
  @GetMapping("getDetail/{id}")
  @SysLog("获取重大危险源表详细信息")
  @ApiOperation("获取重大危险源表详细信息")
  public R getDetail(
      @ApiParam(name = "id", required = true, value = "重大危险源表id") @PathVariable Integer id) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.majorHazardService.getDetail(id, user);
  }

  /**
   * @param form 重大危险源表主键列表
   * @return R
   * @description 重大危险源表删除
   * @auther: 邵锦杰
   */
  @PostMapping("delete")
  @SysLog("重大危险源表删除")
  @ApiOperation("重大危险源表删除")
  public R delete(@Valid @RequestBody SimpleDeleteForm form) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.majorHazardService.delete(form.getIdList(), user);
  }
}
