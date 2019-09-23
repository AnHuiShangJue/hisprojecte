package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.MedicalRecordWord;
import core.entity.PageBean;

import java.util.List;

public interface MedicalRecordWordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MedicalRecordWord record);

    int insertSelective(MedicalRecordWord record);

    MedicalRecordWord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MedicalRecordWord record);

    int updateByPrimaryKey(MedicalRecordWord record);

    List<MedicalRecordWord> list(PageBean<MedicalRecordWord> pageBean);
}