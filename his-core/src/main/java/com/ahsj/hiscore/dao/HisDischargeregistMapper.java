package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDischargeregist;

public interface HisDischargeregistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisDischargeregist record);

    int insertSelective(HisDischargeregist record);

    HisDischargeregist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDischargeregist record);

    int updateByPrimaryKey(HisDischargeregist record);
}