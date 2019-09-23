package com.ahsj.baseuserinfo.services;

import com.ahsj.baseuserinfo.entity.Role;
import com.ahsj.baseuserinfo.entity.SelectEntity;
import com.ahsj.baseuserinfo.entity.TreeEntity;
import com.ahsj.baseuserinfo.entity.UserInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RoleService
 * <p>
 * Date:     2019/9/9 18:00
 *
 * @author XJP
 * @create 2019/9/9
 * @since 1.0.0
 */

public interface RoleService {

    List<Role> selectRoleByUserId(Long id);

    Object selectById(Long id);

    Object updateInit(Long id);

    List<SelectEntity> listUserInfo(UserInfo sysUserInfo);

    Message saveOrUpdate(Role role);

    Message delete(Long[] id);

    PageBean<Role> list(PageBean<Role> pageBean);

    Message roleAddUser(Long[] userIds, Long roleId);

    Message setPermission(String[] permissionIds, Long roleId, String[] organizationIds);

    List<TreeEntity> listMenuTree(Role sysRole);
}
