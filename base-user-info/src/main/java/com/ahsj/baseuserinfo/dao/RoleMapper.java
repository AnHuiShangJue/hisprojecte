package com.ahsj.baseuserinfo.dao;

import com.ahsj.baseuserinfo.entity.Role;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.Nullable;

import java.util.List;


@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    Role selecByRoleName(@Nullable String roleName);

     List<Role> list(PageBean<Role> pageEntity);

    public List<Role> listSysRole(String companyId);

    public List<Role> listAll();

    public void update(Role sysRole);
}