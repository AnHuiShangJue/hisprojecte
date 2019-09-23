package com.ahsj.sysuserinfo.dao;

import com.ahsj.sysuserinfo.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

@Mapper
public interface UserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /**
     *@Description   设置用户权限关联表指定roleId的
     *@Params [sysRoleUser]
     *@return void
     *@Author chenzhicai
     *@Date 2019-04-09
     *@Time 14:15
    **/
    void insertBatch(@NotNull ArrayList<UserRole> lstUserRole);

    /**   删除用户权限关联表指定roleId的用户Id
     *@Description
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-09
     *@Time 14:11
    **/
     void deleteBatch(@NotNull UserRole sysRoleUser);
}