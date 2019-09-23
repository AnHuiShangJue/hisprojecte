package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.NursingRecordTemplate;

import java.util.List;

public interface NursingRecordTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NursingRecordTemplate record);

    int insertSelective(NursingRecordTemplate record);

    NursingRecordTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NursingRecordTemplate record);

    int updateByPrimaryKey(NursingRecordTemplate record);

    List<NursingRecordTemplate> listForMedical(Long userId);
}