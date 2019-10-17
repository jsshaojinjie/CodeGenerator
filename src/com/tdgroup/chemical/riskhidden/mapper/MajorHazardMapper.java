package com.tdgroup.chemical.riskhidden.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tdgroup.chemical.riskhidden.entity.MajorHazard;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardPagingForm;
import com.tdgroup.chemical.riskhidden.pojo.majorhazard.MajorHazardPagingInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MajorHazardMapper extends BaseMapper<MajorHazard> {
  List<MajorHazardPagingInfo> selectPageList(Page<MajorHazardPagingInfo> page,
      @Param("form") MajorHazardPagingForm form);
}
