package com.ahsj.sysuserinfo.dao;

import com.ahsj.sysuserinfo.entity.Role;
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

    /**
     *@功能说明 
     *@Params [pageEntity]
     *@return java.util.List<com.ahsj.sysuserinfo.entity.Role>
     *@Author XJP
     *@Date 2019/9/9
     *@Time 11:21
    **/
     List<Role> list(PageBean<Role> pageBean);

     List<Role> listSysRole(String companyId);

     List<Role> listAll();

     void update(Role sysRole);
}