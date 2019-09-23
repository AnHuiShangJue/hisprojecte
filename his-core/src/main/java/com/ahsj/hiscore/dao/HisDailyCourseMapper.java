package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDailyCourse;

public interface HisDailyCourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisDailyCourse record);

    int insertSelective(HisDailyCourse record);

    HisDailyCourse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDailyCourse record);

    int updateByPrimaryKey(HisDailyCourse record);
}