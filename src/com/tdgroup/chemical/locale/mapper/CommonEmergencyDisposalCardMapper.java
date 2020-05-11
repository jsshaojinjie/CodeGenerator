package com.tdgroup.chemical.locale.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tdgroup.chemical.locale.entity.CommonEmergencyDisposalCard;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardPagingForm;
import com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard.CommonEmergencyDisposalCardPagingInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonEmergencyDisposalCardMapper extends BaseMapper<CommonEmergencyDisposalCard> {
  List<CommonEmergencyDisposalCardPagingInfo> selectPageList(
      Page<CommonEmergencyDisposalCardPagingInfo> page,
      @Param("form") CommonEmergencyDisposalCardPagingForm form);
}
