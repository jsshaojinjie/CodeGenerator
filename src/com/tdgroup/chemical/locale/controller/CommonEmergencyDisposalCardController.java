package com.tdgroup.chemical.locale.controller;

import com.tdgroup.chemical.common.core.util.R;
import com.tdgroup.chemical.common.log.annotation.SysLog;
import com.tdgroup.chemical.common.security.service.ChemicalUser;
import com.tdgroup.chemical.common.security.util.SecurityUtils;
import com.tdgroup.chemical.locale.pojo.SimpleDeleteForm;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardEditForm;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardPagingForm;
import com.tdgroup.chemical.locale.service.ICommonEmergencyDisposalCardService;
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
@RequestMapping("commonEmergencyDisposalCard")
@Api(
    tag = "应急处置卡模块"
)
@AllArgsConstructor
public class CommonEmergencyDisposalCardController {
  private final ICommonEmergencyDisposalCardService commonEmergencyDisposalCardService;

  /**
   * @param form 应急处置卡编辑信息
   * @return R
   * @description 新增与编辑应急处置卡信息
   * @auther: 邵锦杰
   */
  @PostMapping("edit")
  @SysLog("新增与编辑应急处置卡")
  @ApiOperation("新增与编辑应急处置卡")
  public R edit(@Valid @RequestBody CommonEmergencyDisposalCardEditForm form) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.commonEmergencyDisposalCardService.edit(form, user);
  }

  /**
   * @param form 应急处置卡分页查询信息
   * @return R
   * @description 分页获取应急处置卡信息
   * @auther: 邵锦杰
   */
  @PostMapping("listPaging")
  @SysLog("分页获取应急处置卡")
  @ApiOperation("分页获取应急处置卡")
  public R listPaging(@Valid @RequestBody CommonEmergencyDisposalCardPagingForm form) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.commonEmergencyDisposalCardService.listPaging(form, user);
  }

  /**
   * @param id 应急处置卡id
   * @return R
   * @description 获取应急处置卡编辑信息
   * @auther: 邵锦杰
   */
  @GetMapping("getEditInfo/{id}")
  @SysLog("获取应急处置卡编辑信息")
  @ApiOperation("获取应急处置卡编辑信息")
  public R getEditInfo(
      @ApiParam(name = "id", required = true, value = "应急处置卡id") @PathVariable Integer id) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.commonEmergencyDisposalCardService.getEditInfo(id, user);
  }

  /**
   * @param id 应急处置卡id
   * @return R
   * @description 获取应急处置卡详细信息
   * @auther: 邵锦杰
   */
  @GetMapping("getDetail/{id}")
  @SysLog("获取应急处置卡详细信息")
  @ApiOperation("获取应急处置卡详细信息")
  public R getDetail(
      @ApiParam(name = "id", required = true, value = "应急处置卡id") @PathVariable Integer id) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.commonEmergencyDisposalCardService.getDetail(id, user);
  }

  /**
   * @param form 应急处置卡主键列表
   * @return R
   * @description 应急处置卡删除
   * @auther: 邵锦杰
   */
  @PostMapping("delete")
  @SysLog("应急处置卡删除")
  @ApiOperation("应急处置卡删除")
  public R delete(@Valid @RequestBody SimpleDeleteForm form) {
    ChemicalUser user = SecurityUtils.getUser();
    return this.commonEmergencyDisposalCardService.delete(form.getIdList(), user);
  }
}
