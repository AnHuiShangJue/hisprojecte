package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.TemplateMedicalRecord;

import java.util.List;

public interface TemplateMedicalRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TemplateMedicalRecord record);

    int insertSelective(TemplateMedicalRecord record);

    TemplateMedicalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemplateMedicalRecord record);

    int updateByPrimaryKey(TemplateMedicalRecord record);

    List<TemplateMedicalRecord> listForMedical(Long userId);
}