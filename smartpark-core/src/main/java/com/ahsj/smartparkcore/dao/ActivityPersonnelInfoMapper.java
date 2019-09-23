package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityPersonnelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityPersonnelInfo record);

    int insertSelective(ActivityPersonnelInfo record);

    ActivityPersonnelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityPersonnelInfo record);

    int updateByPrimaryKey(ActivityPersonnelInfo record);
}