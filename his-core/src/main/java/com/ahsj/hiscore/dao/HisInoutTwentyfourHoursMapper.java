package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisInoutTwentyfourHours;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HisInoutTwentyfourHoursMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisInoutTwentyfourHours record);

    int insertSelective(HisInoutTwentyfourHours record);

    HisInoutTwentyfourHours selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisInoutTwentyfourHours record);

    int updateByPrimaryKey(HisInoutTwentyfourHours record);
}