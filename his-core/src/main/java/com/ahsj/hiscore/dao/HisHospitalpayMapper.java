package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHospitalpay;

public interface HisHospitalpayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisHospitalpay record);

    int insertSelective(HisHospitalpay record);

    HisHospitalpay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisHospitalpay record);

    int updateByPrimaryKey(HisHospitalpay record);
}