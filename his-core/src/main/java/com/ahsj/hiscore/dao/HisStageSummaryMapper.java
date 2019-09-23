package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisStageSummary;

public interface HisStageSummaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisStageSummary record);

    int insertSelective(HisStageSummary record);

    HisStageSummary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisStageSummary record);

    int updateByPrimaryKey(HisStageSummary record);

    HisStageSummary selectByHosNumber(String number);
}