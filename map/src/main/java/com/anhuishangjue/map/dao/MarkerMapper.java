package com.anhuishangjue.map.dao;

import com.anhuishangjue.map.entity.Marker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Marker record);

    int insertSelective(Marker record);

    Marker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Marker record);

    int updateByPrimaryKey(Marker record);
}