package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.AccessInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccessInfo record);

    int insertSelective(AccessInfo record);

    AccessInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccessInfo record);

    int updateByPrimaryKey(AccessInfo record);
}