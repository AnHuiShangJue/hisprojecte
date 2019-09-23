package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisNurseHandover;

public interface HisNurseHandoverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisNurseHandover record);

    int insertSelective(HisNurseHandover record);

    HisNurseHandover selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisNurseHandover record);

    int updateByPrimaryKey(HisNurseHandover record);
}