package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.StationInfo;

public interface StationInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StationInfo record);

    int insertSelective(StationInfo record);

    StationInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StationInfo record);

    int updateByPrimaryKey(StationInfo record);
}