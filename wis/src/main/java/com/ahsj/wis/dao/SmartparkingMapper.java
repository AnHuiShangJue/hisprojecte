package com.ahsj.wis.dao;

import com.ahsj.wis.entity.Smartparking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmartparkingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Smartparking record);

    int insertSelective(Smartparking record);

    Smartparking selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Smartparking record);

    int updateByPrimaryKey(Smartparking record);

    List<Smartparking> selectAll();

}