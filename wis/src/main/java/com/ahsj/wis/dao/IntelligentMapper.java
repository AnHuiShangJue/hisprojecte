package com.ahsj.wis.dao;

import com.ahsj.wis.entity.Intelligent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntelligentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Intelligent record);

    int insertSelective(Intelligent record);

    Intelligent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Intelligent record);

    int updateByPrimaryKey(Intelligent record);

    List<Intelligent> selectAll();
}