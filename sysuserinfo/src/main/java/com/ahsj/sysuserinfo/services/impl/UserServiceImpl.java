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
package com.ahsj.sysuserinfo.services.impl;

import com.ahsj.sysuserinfo.dao.UserInfoMapper;
import com.ahsj.sysuserinfo.entity.UserInfo;
import com.ahsj.sysuserinfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.FrameworkServlet;
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

    @Override
    @Transactional(readOnly = true)
    public List<UserInfo> getUserInfoDeptById(String deptId) {
        return userInfoMapper.selectByDeptId(deptId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo selectByPrimaryKey(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserLoginId(String userId) {
        return userInfoMapper.getUserLoginId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserId(Long id) {
        return userInfoMapper.getUserId(id);
    }

    /**
     * @return com.ahsj.sysuserinfo.entity.UserInfo
     * @功能说明
     * @Params [username]
     * @Author XJP
     * @Date 2019/9/7
     * @Time 13:36
     **/
    @Override
    public UserInfo selectByUserName(String username) {
        if (EmptyUtil.Companion.isNullOrEmpty(username)){
            return new UserInfo();
        }else {
            UserInfo userInfo = userInfoMapper.selectByUserName(username);
            if (EmptyUtil.Companion.isNullOrEmpty(userInfo)){
                return new UserInfo();
            }else {
                return userInfo;
            }
        }
    }
}
