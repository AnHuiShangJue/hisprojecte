package com.ahsj.wxapplet.services;


import com.ahsj.wxapplet.entity.UserInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface UserService {

    /**
     * @Description 新增
     * @Author  muxu
     * @Date  2019/10/17
     * @Time 13:11
     * @Return sun.plugin2.message.Message
     * @Params [userInfo]
    **/
    Message save(String openid)throws Exception;

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/10/17
     * @Time 13:11
     * @Return
     * @Params
    **/
    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> getUserInfoDeptById(String deptId);

    UserInfo getUserLoginId(String userId);

    UserInfo getUserId(Long id);

    Message saveOrUpdateUserInfo(UserInfo user);

    Message delete(Long[] id);

    UserInfo selectByUserName(String username);

    PageBean<UserInfo> list(PageBean<UserInfo> pageBean);

    UserInfo selectById(Long id);
}
