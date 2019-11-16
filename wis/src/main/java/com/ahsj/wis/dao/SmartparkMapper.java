package com.ahsj.wis.dao;

import com.ahsj.wis.entity.Smartpark;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmartparkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Smartpark record);

    int insertSelective(Smartpark record);

    Smartpark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Smartpark record);

    int updateByPrimaryKey(Smartpark record);

    List<Smartpark> selectAll();

}