//package com.ahsj.userinfor.services;
//
//import com.ahsj.baseuserinfo.entity.UserInfo
//import core.entity.PageBean
//
//
//public  interface UserService {
//
//    /**
//     *@Description  插入一条用户信息,权限级别是管理员级别
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-03-15
//     *@Time 15:07
//    **/
//    UserInfo saveOrUpdateUserInfo(UserInfo UserInfo);
//
//
//    /**
//     *@Description  通过Id查找用户
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-04-08
//     *@Time 0:26
//    **/
//    fun selectById(id: Long): UserInfo
//
//    /**
//     *@Description 根据用户名查询用户信息
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-03-15
//     *@Time 18:24
//    **/
//    fun selectByUserName(userName: @NotNull String): UserInfo?
//
//    /**
//     *@Description  list用户表
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-04-14
//     *@Time 21:14
//    **/
//    fun list(pageBean: PageBean<UserInfo>): PageBean<UserInfo>
//
//    /**
//     *@Description 删除
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-04-14
//     *@Time 21:14
//    **/
//    fun delete(ids:Array<Long>):Message
//
//
//
//}