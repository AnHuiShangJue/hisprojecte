package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisVisitCardRecord;

public interface HisVisitCardRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisVisitCardRecord record);

    int insertSelective(HisVisitCardRecord record);

    HisVisitCardRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisVisitCardRecord record);

    int updateByPrimaryKey(HisVisitCardRecord record);
}