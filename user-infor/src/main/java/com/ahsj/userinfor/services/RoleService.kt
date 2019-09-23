package com.ahsj.userinfor.services

import com.ahsj.userinfor.entity.Role
import com.ahsj.userinfor.entity.SelectEntity
import com.ahsj.userinfor.entity.TreeEntity
import com.ahsj.userinfor.entity.UserInfo
import core.entity.PageBean
import core.message.Message
import org.jetbrains.annotations.NotNull

interface RoleService {


    @Throws(Exception::class)
    abstract fun list(pageEntity: PageBean<Role>): PageBean<Role>

    /**
     *@Description 根据ID查询Role
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-03-16
     *@Time 17:29
    **/
    @Throws(Exception::class)
    fun selectRoleByUserId(@NotNull id:Long):List<Role>


    /**
     *@Description 新增或更新角色
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-08
     *@Time 12:22
    **/
    @Throws(Exception::class)
    fun saveOrUpdate(@NotNull role: Role):Message

    /**
     *@Description 删除角色
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-08
     *@Time 12:24
    **/
    @Throws(Exception::class)
    fun delete(@NotNull id:Array<Long>):Message

   /**
    *@Description 给角色添加用户
    *@Params
    *@return
    *@Author chenzhicai
    *@Date 2019-04-08
    *@Time 16:11
   **/
   @Throws(Exception::class)
   fun roleAddUser(userIds:Array<Long>,roleId:Long):Message


    /**
     *@Description  给角色添加权限
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-08
     *@Time 17:14
    **/
    @Throws(Exception::class)
    fun setPermission(permissionIds :Array<String>, roleId:Long,organizationIds :Array<String>):Message

    @Throws(Exception::class)
    fun selectById(id: Long): Role

    @Throws(Exception::class)
    abstract fun listMenuTree(sysRole: Role): List<TreeEntity>

    @Throws(Exception::class)
    abstract  fun updateInit(id: Long): Role

    fun listUserInfo(sysUserInfo: UserInfo): MutableList<SelectEntity>


}