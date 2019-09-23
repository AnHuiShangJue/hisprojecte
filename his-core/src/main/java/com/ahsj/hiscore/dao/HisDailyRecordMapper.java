package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDailyRecord;

public interface HisDailyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HisDailyRecord record);

    int insertSelective(HisDailyRecord record);

    HisDailyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HisDailyRecord record);

    int updateByPrimaryKey(HisDailyRecord record);
}