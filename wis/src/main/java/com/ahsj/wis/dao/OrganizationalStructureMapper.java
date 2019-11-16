package com.ahsj.wis.dao;

import com.ahsj.wis.entity.OrganizationalStructure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationalStructureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrganizationalStructure record);

    int insertSelective(OrganizationalStructure record);

    OrganizationalStructure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrganizationalStructure record);

    int updateByPrimaryKey(OrganizationalStructure record);


    List<OrganizationalStructure> selectAll();
}