package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.Region;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Region region);

    int insertSelective(Region region);

    Region selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Region region);

    int updateByPrimaryKey(Region region);
}