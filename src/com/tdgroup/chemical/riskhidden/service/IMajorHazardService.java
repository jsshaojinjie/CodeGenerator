package com.tdgroup.chemical.riskhidden.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tdgroup.chemical.common.core.util.R;
import com.tdgroup.chemical.common.security.service.ChemicalUser;
import com.tdgroup.chemical.riskhidden.entity.MajorHazard;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardEditForm;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardPagingForm;
import java.lang.Integer;
import java.util.List;

public interface IMajorHazardService extends IService<MajorHazard> {
  R edit(MajorHazardEditForm form, ChemicalUser user);

  R listPaging(MajorHazardPagingForm form, ChemicalUser user);

  R getEditInfo(Integer id, ChemicalUser user);

  R getDetail(Integer id, ChemicalUser user);

  R delete(List idList, ChemicalUser user);
}
