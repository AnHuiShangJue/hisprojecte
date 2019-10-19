//package com.ahsj.userinfor.services.impl
//
//import com.ahsj.baseuserinfo.common.CodeHelper
//import com.ahsj.baseuserinfo.dao.UserInfoMapper
//import com.ahsj.baseuserinfo.entity.UserInfo
//import com.ahsj.userinfor.services.UserService
//import core.entity.PageBean
//import core.message.Message
//import core.message.MessageUtil
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import utils.EmptyUtil
//
//
//@Service
//class UserServiceImpl(@Autowired val userInfoMapper: UserInfoMapper) : UserService {
//
//
//    /**
//     *@Description 通过用户名称查找用户
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-06-14
//     *@Time 13:43
//     **/
//    override fun selectByUserName(userName: String): UserInfo? {
//        val user = userInfoMapper.selectByUserName(userName)
//        return user
//    }
//
//
//    /**
//     *@Description 通过id查找用户
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-06-14
//     *@Time 13:43
//     **/
//    override fun selectById(id: Long): UserInfo {
//        return CodeHelper.getInstance().setCodeValue(userInfoMapper.selectByPrimaryKey(id))
//    }
//
//    override fun saveOrUpdateUserInfo(userInfo: UserInfo): Message {
//        //预留检测模块，根据具体的业务进行检索传入的值进行安全规则校验
//        if (!EmptyUtil.isNullOrEmpty(userInfo.userName)) {
//            if (EmptyUtil.isNullOrEmpty(userInfo.id)) {
//                val check = userInfoMapper.selectByUserName(userInfo.userName)
//                //先判定库中是否存在该用户
//                //不存在就插入用户
//                if (EmptyUtil.isNullOrEmpty(check)) {
//                    userInfoMapper.insert(userInfo);
//                    return MessageUtil.createMessage(true, "创建用户${userInfo.userName}成功!")
//                }
//                //存在就提示创建失败
//                else {
//                    return MessageUtil.createMessage(false, "创建用户${userInfo.userName}失败，该账户已经存在!")
//                }
//            } else {
//                val check = userInfoMapper.selectByPrimaryKey(userInfo.id)
//                //只有用户名相同且id相同才可以更新
//                if (!EmptyUtil.isNullOrEmpty(check)) {
//                    userInfoMapper.updateByPrimaryKey(userInfo)
//                    return MessageUtil.createMessage(true, "更新用户${userInfo.userName}成功!")
//                } else {
//                    return MessageUtil.createMessage(false, "更新用户${userInfo.userName}失败，该账户不存在!")
//                }
//            }
//        } else {
//            return MessageUtil.createMessage(false, "创建用户失败!所输入的账号不合法!")
//        }
//    }
//
//    /**
//     *@Description 查询list
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-06-14
//     *@Time 13:43
//     **/
//    override fun list(pageBean: PageBean<UserInfo>): PageBean<UserInfo> {
//        pageBean.setData(CodeHelper.getInstance().setCodeValue(userInfoMapper.list(pageBean)))
//        return pageBean
//    }
//
//    /**
//     *@Description 删除
//     *@Params
//     *@return
//     *@Author chenzhicai
//     *@Date 2019-06-14
//     *@Time 13:43
//     **/
//    override fun delete(ids: Array<Long>): Message {
//        for (index in 0..ids.size - 1 step 1) {
//            userInfoMapper.deleteByPrimaryKey(ids.get(index))
//        }
//        return MessageUtil.createMessage(true, "删除成功！")
//    }
//
//
//}
//
//
//fun test() {
//
//}