package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDieintwentyfour;

public interface HisDieintwentyfourMapper extends BaseMapper<HisDieintwentyfour>{
    int deleteByPrimaryKey(Long id);

    int insert(HisDieintwentyfour record);

    int insertSelective(HisDieintwentyfour record);

    HisDieintwentyfour selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDieintwentyfour record);

    int updateByPrimaryKey(HisDieintwentyfour record);
}