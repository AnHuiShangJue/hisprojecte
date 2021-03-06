package com.ahsj.userinfor.dao;

import com.ahsj.userinfor.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    void insertBatch(@NotNull ArrayList<RolePermission> lstRolePermission);

    void deletByRoleId(long roleId);

     List<RolePermission> selectPermission(RolePermission rolePermission);

    void deleteByRoleId( Long roleId);
}