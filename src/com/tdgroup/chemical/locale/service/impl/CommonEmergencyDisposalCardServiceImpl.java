package com.tdgroup.chemical.locale.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tdgroup.chemical.common.core.constant.CommonConstants;
import com.tdgroup.chemical.common.core.util.R;
import com.tdgroup.chemical.common.security.service.ChemicalUser;
import com.tdgroup.chemical.locale.entity.CommonEmergencyDisposalCard;
import com.tdgroup.chemical.locale.mapper.CommonEmergencyDisposalCardMapper;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardDetail;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardEditForm;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardEditInfo;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardPagingForm;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardPagingInfo;
import com.tdgroup.chemical.locale.service.ICommonEmergencyDisposalCardService;
import java.lang.Integer;
import java.lang.Override;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommonEmergencyDisposalCardServiceImpl extends ServiceImpl<CommonEmergencyDisposalCardMapper, CommonEmergencyDisposalCard> implements ICommonEmergencyDisposalCardService {
  @Override
  public R edit(CommonEmergencyDisposalCardEditForm form, ChemicalUser user) {
    CommonEmergencyDisposalCard commonEmergencyDisposalCard = new CommonEmergencyDisposalCard();
     if (form.getId() != null) {
        commonEmergencyDisposalCard = this.getById(form.getId());
        if (commonEmergencyDisposalCard == null) {
           return R.builder().code(CommonConstants.FAIL).msg("不存在此应急处置卡信息").build();
        }
        commonEmergencyDisposalCard.setUpdateTime(LocalDateTime.now());
        } else {
           commonEmergencyDisposalCard.setCompanyId(user.getCompanyId());
           commonEmergencyDisposalCard.setCreateTime(LocalDateTime.now());
           commonEmergencyDisposalCard.setUpdateTime(LocalDateTime.now());
           commonEmergencyDisposalCard.setEffectiveSign(CommonConstants.EFFECTIVESIGN);
        };
    BeanUtils.copyProperties(form, commonEmergencyDisposalCard);
        this.saveOrUpdate(commonEmergencyDisposalCard);
        return R.builder().build();
  }

  @Override
  public R listPaging(CommonEmergencyDisposalCardPagingForm form, ChemicalUser user) {
    form.setCompanyId(user.getCompanyId());
    Page<CommonEmergencyDisposalCardPagingInfo> page = new Page<CommonEmergencyDisposalCardPagingInfo>(form.getPage().getCurrent(), form.getPage().getSize());
                List<CommonEmergencyDisposalCardPagingInfo> records = this.baseMapper.selectPageList(page, form);
                page.setRecords(records);
                return R.builder().data(page).build();
  }

  @Override
  public R getEditInfo(Integer id, ChemicalUser user) {
      CommonEmergencyDisposalCard commonEmergencyDisposalCard = this.getById(id);
                if (commonEmergencyDisposalCard == null) {
                    return R.builder().code(CommonConstants.FAIL).msg("不存在此应急处置卡信息").build();
                } else if (!commonEmergencyDisposalCard.getCompanyId().equals(user.getCompanyId())) {
                    return R.builder().code(CommonConstants.FAIL).msg("您无权编辑此信息").build();
                }
                CommonEmergencyDisposalCardEditInfo result = new CommonEmergencyDisposalCardEditInfo();
                BeanUtils.copyProperties(commonEmergencyDisposalCard, result);
                return R.builder().data(result).build();
  }

  @Override
  public R getDetail(Integer id, ChemicalUser user) {
      CommonEmergencyDisposalCard commonEmergencyDisposalCard = this.getById(id);
                if (commonEmergencyDisposalCard == null) {
                    return R.builder().code(CommonConstants.FAIL).msg("不存在此应急处置卡信息").build();
                } else if (!commonEmergencyDisposalCard.getCompanyId().equals(user.getCompanyId())) {
                    return R.builder().code(CommonConstants.FAIL).msg("您无权查看此信息").build();
                }
                CommonEmergencyDisposalCardDetail result = new CommonEmergencyDisposalCardDetail();
                BeanUtils.copyProperties(commonEmergencyDisposalCard, result);
                return R.builder().data(result).build();
  }

  @Override
  public R delete(List idList, ChemicalUser user) {
     if (idList.size() > 0) {
                    this.remove(new QueryWrapper<CommonEmergencyDisposalCard>().lambda().in(CommonEmergencyDisposalCard::getId, idList).eq(CommonEmergencyDisposalCard::getCompanyId, user.getCompanyId()));
                }
                return R.builder().build();
  }
}
