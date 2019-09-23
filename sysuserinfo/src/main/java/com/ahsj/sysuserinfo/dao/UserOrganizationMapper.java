package com.ahsj.sysuserinfo.dao;


import com.ahsj.sysuserinfo.entity.UserOrganization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrganizationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserOrganization record);

    int insertSelective(UserOrganization record);

    UserOrganization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserOrganization record);

    int updateByPrimaryKey(UserOrganization record);

   /**
    *@Description 批量插入
    *@Params [sysUserDept]
    *@return void
    *@Author chenzhicai
    *@Date 2019-04-11
    *@Time 20:15
   **/
     void importBatch(List<UserOrganization> lstSysUserDept);

  /**
   *@Description 批量删除
   *@Params [sysUserDept]
   *@return void
   *@Author chenzhicai
   *@Date 2019-04-11
   *@Time 20:15
  **/
     void deleteByUserId(UserOrganization sysUserDept);

   /**
    *@Description 设为负责人
    *@Params []
    *@return void
    *@Author chenzhicai
    *@Date 2019-04-11
    *@Time 20:15
   **/
     void updateForHead(UserOrganization sysUserDept);

   /**
    *@Description 删除初始数据
    *@Params
    *@return
    *@Author chenzhicai
    *@Date 2019-04-11
    *@Time 20:15
   **/
     void deleteInitData();
}