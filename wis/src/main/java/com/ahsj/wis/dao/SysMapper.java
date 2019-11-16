package com.ahsj.wis.dao;

import com.ahsj.wis.entity.Sys;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sys record);

    int insertSelective(Sys record);

    Sys selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sys record);

    int updateByPrimaryKey(Sys record);

    List<Sys> getSysAll();
}