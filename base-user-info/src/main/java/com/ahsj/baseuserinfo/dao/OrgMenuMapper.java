package com.ahsj.baseuserinfo.dao;


import com.ahsj.baseuserinfo.entity.OrgMenu;
import com.ahsj.baseuserinfo.entity.Role;
import com.ahsj.baseuserinfo.entity.TreeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrgMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgMenu record);

    int insertSelective(OrgMenu record);

    OrgMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgMenu record);

    int updateByPrimaryKey(OrgMenu record);

    void importBatch(List<OrgMenu> listOrgMenu);

    void deleteByMenuId(OrgMenu orgMenu);

    List<TreeEntity> listMenuTree(Role sysRole);

    void deleteAll();
}