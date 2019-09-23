package com.ahsj.baseuserinfo.dao;


import com.ahsj.baseuserinfo.entity.Role;
import com.ahsj.baseuserinfo.entity.RoleOrganization;
import com.ahsj.baseuserinfo.entity.TreeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleOrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleOrganization record);

    int insertSelective(RoleOrganization record);

    RoleOrganization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleOrganization record);

    int updateByPrimaryKey(RoleOrganization record);

    List<TreeEntity> listOrgnizationMenuTree(Role role);
}