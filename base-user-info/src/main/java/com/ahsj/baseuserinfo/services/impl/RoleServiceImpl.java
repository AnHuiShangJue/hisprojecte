package com.ahsj.baseuserinfo.services.impl;

import com.ahsj.baseuserinfo.dao.*;
import com.ahsj.baseuserinfo.entity.*;
import com.ahsj.baseuserinfo.services.RoleService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RoleServiceImpl
 * <p>
 * Date:     2019/9/9 18:01
 *
 * @author XJP
 * @create 2019/9/9
 * @since 1.0.0
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Role> selectRoleByUserId(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Role selectById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role updateInit(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SelectEntity> listUserInfo(UserInfo sysUserInfo) {
        return userInfoMapper.listUserInfo(sysUserInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Message saveOrUpdate(Role role) {
        if (EmptyUtil.Companion.isNullOrEmpty(role.getId())) {
            //执行插入角色逻辑
            if (!EmptyUtil.Companion.isNullOrEmpty(role.getRoleName())) {
                Role check = roleMapper.selecByRoleName(role.getRoleName());
                if (EmptyUtil.Companion.isNullOrEmpty(check)) {
                    roleMapper.insert(role);
                    return MessageUtil.createMessage(true, "创建角色成功!");
                } else {
                    return MessageUtil.createMessage(false, "创建角色失败!该角色已经存在!");
                }
            } else {
                return MessageUtil.createMessage(false, "创建角色失败!输入信息不合法!");
            }
        }
        //执行更新逻辑
        else {
            Role check = roleMapper.selectByPrimaryKey(role.getId());
            //如果角色存在
            if (!EmptyUtil.Companion.isNullOrEmpty(check)) {
                roleMapper.updateByPrimaryKeySelective(role);
                return MessageUtil.createMessage(false, "更新角色成功!");
            }
            //如果角色不存在
            else {
                return MessageUtil.createMessage(false, "更新角色失败!该角色不存在!");
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) {
        for (Long id : ids) {
            roleMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<Role> list(PageBean<Role> pageBean) {
        pageBean.setData(roleMapper.list(pageBean));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public Message roleAddUser(Long[] userIds, Long roleId) {
        List<UserRole> lstUserRole =new  ArrayList<>();
        UserRole userRole = new  UserRole();
        Integer i = 0;

        for (Long userId : userIds) {
            UserRole userR = new UserRole();
            userR.setRoleId(roleId);
            userR.setUserId(userId);
            lstUserRole.add(userR);
        }
        //先删除之前的关联数据
        userRole.setRoleId(roleId);
        userRole.setUserIds(userIds);
        userRoleMapper.deleteBatch(userRole);
        //再重新插入
        userRoleMapper.insertBatch(lstUserRole);
        return MessageUtil.createMessage(true,"用户添加成功！");
    }

    @Override
    @Transactional(readOnly = false)
    public Message setPermission(String[] permissionIds, Long roleId, String[] organizationIds) {
        List<RolePermission> lstRolePermission =new ArrayList<>();
        List<Organization>  organizationPerms =new  ArrayList<>();
        List<Menu> menuPerms =new  ArrayList<Menu>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setMenuTreeId(permissionId);
            lstRolePermission.add(rolePermission);
        }
        // 根据角色ID删除角色权限表
        rolePermissionMapper.deletByRoleId(roleId);
        // 批量插入
        rolePermissionMapper.insertBatch(lstRolePermission);
        return MessageUtil.createMessage(true,"权限设置成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreeEntity> listMenuTree(Role sysRole) {
        return menuMapper.listMenuTree(sysRole);
    }
}
