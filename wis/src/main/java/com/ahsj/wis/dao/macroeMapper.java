package com.ahsj.wis.dao;

import com.ahsj.wis.entity.macroe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface macroeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(macroe record);

    int insertSelective(macroe record);

    macroe selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(macroe record);

    int updateByPrimaryKey(macroe record);

    List<macroe> selectAll();

}