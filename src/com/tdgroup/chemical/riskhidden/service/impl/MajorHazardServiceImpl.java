package com.tdgroup.chemical.riskhidden.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tdgroup.chemical.common.core.constant.CommonConstants;
import com.tdgroup.chemical.common.core.util.R;
import com.tdgroup.chemical.common.security.service.ChemicalUser;
import com.tdgroup.chemical.riskhidden.entity.MajorHazard;
import com.tdgroup.chemical.riskhidden.mapper.MajorHazardMapper;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardDetail;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardEditForm;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardEditInfo;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardPagingForm;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardPagingInfo;
import com.tdgroup.chemical.riskhidden.service.IMajorHazardService;
import java.lang.Integer;
import java.lang.Override;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MajorHazardServiceImpl extends ServiceImpl<MajorHazardMapper, MajorHazard> implements IMajorHazardService {
  @Override
  public R edit(MajorHazardEditForm form, ChemicalUser user) {
    MajorHazard majorHazard = new MajorHazard();
     if (form.getId() != null) {
        majorHazard = this.getById(form.getId());
        if (majorHazard == null) {
           return R.builder().code(CommonConstants.FAIL).msg("不存在此重大危险源表信息").build();
        }
        majorHazard.setUpdateTime(LocalDateTime.now());
        } else {
           majorHazard.setCompanyId(user.getCompanyId());
           majorHazard.setCreateTime(LocalDateTime.now());
           majorHazard.setUpdateTime(LocalDateTime.now());
           majorHazard.setEffectiveSign(CommonConstants.EFFECTIVESIGN);
        };
    BeanUtils.copyProperties(form, majorHazard);
        this.saveOrUpdate(majorHazard);
        return R.builder().build();
  }

  @Override
  public R listPaging(MajorHazardPagingForm form, ChemicalUser user) {
    form.setCompanyId(user.getCompanyId());
    Page<MajorHazardPagingInfo> page = new Page<MajorHazardPagingInfo>(form.getPage().getCurrent(), form.getPage().getSize());
                List<MajorHazardPagingInfo> records = this.baseMapper.selectPageList(page, form);
                page.setRecords(records);
                return R.builder().data(page).build();
  }

  @Override
  public R getEditInfo(Integer id, ChemicalUser user) {
      MajorHazard majorHazard = this.getById(id);
                if (majorHazard == null) {
                    return R.builder().code(CommonConstants.FAIL).msg("不存在此重大危险源表信息").build();
                } else if (!majorHazard.getCompanyId().equals(user.getCompanyId())) {
                    return R.builder().code(CommonConstants.FAIL).msg("您无权编辑此信息").build();
                }
                MajorHazardEditInfo result = new MajorHazardEditInfo();
                BeanUtils.copyProperties(majorHazard, result);
                return R.builder().data(result).build();
  }

  @Override
  public R getDetail(Integer id, ChemicalUser user) {
      MajorHazard majorHazard = this.getById(id);
                if (majorHazard == null) {
                    return R.builder().code(CommonConstants.FAIL).msg("不存在此重大危险源表信息").build();
                } else if (!majorHazard.getCompanyId().equals(user.getCompanyId())) {
                    return R.builder().code(CommonConstants.FAIL).msg("您无权查看此信息").build();
                }
                MajorHazardDetail result = new MajorHazardDetail();
                BeanUtils.copyProperties(majorHazard, result);
                return R.builder().data(result).build();
  }

  @Override
  public R delete(List idList, ChemicalUser user) {
     if (idList.size() > 0) {
                    this.remove(new QueryWrapper<MajorHazard>().lambda().in(MajorHazard::getId, idList).eq(MajorHazard::getCompanyId, user.getCompanyId()));
                }
                return R.builder().build();
  }
}
