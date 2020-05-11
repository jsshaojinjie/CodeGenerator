package com.tdgroup.chemical.locale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tdgroup.chemical.common.core.util.R;
import com.tdgroup.chemical.common.security.service.ChemicalUser;
import com.tdgroup.chemical.locale.entity.CommonEmergencyDisposalCard;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardEditForm;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardPagingForm;
import java.lang.Integer;
import java.util.List;

public interface ICommonEmergencyDisposalCardService extends IService<CommonEmergencyDisposalCard> {
  R edit(CommonEmergencyDisposalCardEditForm form, ChemicalUser user);

  R listPaging(CommonEmergencyDisposalCardPagingForm form, ChemicalUser user);

  R getEditInfo(Integer id, ChemicalUser user);

  R getDetail(Integer id, ChemicalUser user);

  R delete(List idList, ChemicalUser user);
}
