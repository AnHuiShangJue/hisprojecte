package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.NursingRecordWord;
import core.entity.PageBean;

import java.util.List;

public interface NursingRecordWordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NursingRecordWord record);

    int insertSelective(NursingRecordWord record);

    NursingRecordWord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NursingRecordWord record);

    int updateByPrimaryKey(NursingRecordWord record);

    List<NursingRecordWord> list(PageBean<NursingRecordWord> pageBean);
}