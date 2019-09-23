package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.SurgicalRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurgicalRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SurgicalRecord surgicalRecord);

    int insertSelective(SurgicalRecord surgicalRecord);

    SurgicalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurgicalRecord surgicalRecord);

    int updateByPrimaryKey(SurgicalRecord surgicalRecord);
}