package com.ahsj.wxapplet.services.impl;

import com.ahsj.wxapplet.common.CodeHelper;
import com.ahsj.wxapplet.dao.UserInfoMapper;
import com.ahsj.wxapplet.entity.UserInfo;
import com.ahsj.wxapplet.services.UserService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * @Description 新增
     * @Author muxu
     * @Date 2019/10/17
     * @Time 13:13
     * @Return sun.plugin2.message.Message
     * @Params [userInfo]
     **/
    @Override
    @Transactional(readOnly = false)
    public Message save(String openid) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(userInfoMapper.selectByUserId(openid))){
            return MessageUtil.createMessage(true,"已有账号，登录成功！");
        }else {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(openid);
            userInfo.setPassword(openid);
            userInfoMapper.insert(userInfo);
            return MessageUtil.createMessage(true,"账号新增成功！");
        }
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/10/17
     * @Time 13:12
     * @Return sun.plugin2.message.Message
     * @Params [userInfo]
     **/
    @Override
    @Transactional(readOnly = true)
    public UserInfo selectByPrimaryKey(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

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
                UserInfo check = userInfoMapper.selectByUserName(userInfo.getUserName());
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
