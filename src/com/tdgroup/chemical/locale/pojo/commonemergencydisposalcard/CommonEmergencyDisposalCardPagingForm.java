package com.tdgroup.chemical.locale.pojo.commonemergencydisposalcard;

import com.tdgroup.chemical.locale.pojo.PageInfo;
import java.lang.Integer;
import lombok.Data;

@Data
public class CommonEmergencyDisposalCardPagingForm {
  private Integer companyId;

  private PageInfo page;
}
