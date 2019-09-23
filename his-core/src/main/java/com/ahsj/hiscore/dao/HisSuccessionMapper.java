package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisSuccession;

public interface HisSuccessionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisSuccession record);

    int insertSelective(HisSuccession record);

    HisSuccession selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisSuccession record);

    int updateByPrimaryKey(HisSuccession record);

    HisSuccession selectByHosNumber(String number);
}