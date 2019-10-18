/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: UserServiceImpl
 * Author:   anhuishangjue
 * Date:     2019/7/8 18:32
 * Description: UserServiceImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.baseuserinfo.services.impl;

import com.ahsj.baseuserinfo.common.CodeHelper;
import com.ahsj.baseuserinfo.dao.UserInfoMapper;
import com.ahsj.baseuserinfo.entity.UserInfo;
import com.ahsj.baseuserinfo.services.UserService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈UserServiceImpl〉
 *
 * @author anhuishangjue
 * @create 2019/7/8
 * @since 1.0.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * @return java.util.List<com.ahsj.baseuserinfo.entity.UserInfo>
     * @功能说明
     * @Params [deptId]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> getUserInfoDeptById(String deptId) {
        return userInfoMapper.selectByDeptId(deptId);
    }

    /**
     * @return com.ahsj.baseuserinfo.entity.UserInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = true)
    public UserInfo selectByPrimaryKey(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * @return com.ahsj.baseuserinfo.entity.UserInfo
     * @功能说明
     * @Params [userId]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserLoginId(String userId) {
        return userInfoMapper.getUserLoginId(userId);
    }

    /**
     * @return com.ahsj.baseuserinfo.entity.UserInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserId(Long id) {
        return userInfoMapper.getUserId(id);
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [user]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdateUserInfo(UserInfo userInfo) {
        //预留检测模块，根据具体的业务进行检索传入的值进行安全规则校验
        if (!EmptyUtil.Companion.isNullOrEmpty(userInfo.getUserName())) {
            if (EmptyUtil.Companion.isNullOrEmpty(userInfo.getId())) {
                UserInfo check = userInfoMapper.selectByUserName(userInfo.getUserId());
                //先判定库中是否存在该用户
                //不存在就插入用户
                if (EmptyUtil.Companion.isNullOrEmpty(check)) {
                    userInfoMapper.insert(userInfo);
                    return MessageUtil.createMessage(true, "创建用户成功!");
                }
                //存在就提示创建失败
                else {
                    return MessageUtil.createMessage(false, "创建用户失败，该账户已经存在!");
                }
            } else {
                UserInfo check = userInfoMapper.selectByPrimaryKey(userInfo.getId());
                //只有用户名相同且id相同才可以更新
                if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
                    userInfoMapper.updateByPrimaryKey(userInfo);
                    return MessageUtil.createMessage(true, "更新用户成功!");
                } else {
                    return MessageUtil.createMessage(false, "更新用户失败，该账户不存在!");
                }
            }
        } else {
            return MessageUtil.createMessage(false, "创建用户失败!所输入的账号不合法!");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) {
        for (Long id : ids) {
            userInfoMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功！");
    }

    /**
     * @return com.ahsj.baseuserinfo.entity.UserInfo
     * @功能说明
     * @Params [username]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = true)
    public UserInfo selectByUserName(String username) {
       return userInfoMapper.selectByUserName(username);

    }

    /**
     * @return core.entity.PageBean<com.ahsj.baseuserinfo.entity.UserInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:49
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<UserInfo> list(PageBean<UserInfo> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(userInfoMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.baseuserinfo.entity.UserInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 17:50
     **/
    @Override
    @Transactional(readOnly = true)
    public UserInfo selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(userInfoMapper.selectByPrimaryKey(id));
    }
}
