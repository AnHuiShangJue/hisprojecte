package com.ahsj.sysuserinfo.services.impl;

import com.ahsj.sysuserinfo.common.CodeHelper;
import com.ahsj.sysuserinfo.dao.RoleMapper;
import com.ahsj.sysuserinfo.entity.Role;
import com.ahsj.sysuserinfo.entity.SelectEntity;
import com.ahsj.sysuserinfo.entity.TreeEntity;
import com.ahsj.sysuserinfo.entity.UserInfo;
import com.ahsj.sysuserinfo.services.RoleService;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RoleServiceImpl
 * <p>
 * Date:     2019/9/9 11:08
 *
 * @author XJP
 * @create 2019/9/9
 * @since 1.0.0
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public List<Role> selectRoleByUserId(Long id) {
        return null;
    }

    @Override
    public List<SelectEntity> listUserInfo(UserInfo sysUserInfo) {
        return null;
    }

    @Override
    public Message saveOrUpdate(Role role) {
        return null;
    }

    @Override
    public Message delete(Long[] id) {
        return null;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.sysuserinfo.entity.Role>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:21
     **/
    @Override
    public PageBean<Role> list(PageBean<Role> pageBean) {
        pageBean.setData(roleMapper.list(pageBean));
        return pageBean;
    }

    @Override
    public Message roleAddUser(Long[] userIds, Long roleId) {
        return null;
    }

    @Override
    public Message setPermission(String[] permissionIds, Long roleId, String[] organizationIds) {
        return null;
    }

    @Override
    public List<TreeEntity> listMenuTree(Role sysRole) {
        return null;
    }
}
