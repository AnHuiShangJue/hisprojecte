package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.MedicalCrisisNotice;

public interface MedicalCrisisNoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MedicalCrisisNotice record);

    int insertSelective(MedicalCrisisNotice record);

    MedicalCrisisNotice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MedicalCrisisNotice record);

    int updateByPrimaryKey(MedicalCrisisNotice record);

    MedicalCrisisNotice selectByHosNumber(String number);
}