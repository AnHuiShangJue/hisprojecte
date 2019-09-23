package com.ahsj.sysuserinfo.services;

import com.ahsj.sysuserinfo.entity.Role;
import com.ahsj.sysuserinfo.entity.SelectEntity;
import com.ahsj.sysuserinfo.entity.TreeEntity;
import com.ahsj.sysuserinfo.entity.UserInfo;
import core.entity.PageBean;
import core.message.Message;
import kotlin.jvm.Throws;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: RoleService
 * <p>
 * Date:     2019/9/9 11:08
 *
 * @author XJP
 * @create 2019/9/9
 * @since 1.0.0
 */

public interface RoleService {

    /**
     * @return java.util.List<com.ahsj.sysuserinfo.entity.Role>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    List<Role> selectRoleByUserId(Long id);

    /**
     * @return java.util.List<com.ahsj.sysuserinfo.entity.SelectEntity>
     * @功能说明
     * @Params [sysUserInfo]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    List<SelectEntity> listUserInfo(UserInfo sysUserInfo);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [role]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    Message saveOrUpdate(Role role);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    Message delete(Long[] id);

    /**
     * @return core.entity.PageBean<com.ahsj.sysuserinfo.entity.Role>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    PageBean<Role> list(PageBean<Role> pageBean);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [userIds, roleId]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    Message roleAddUser(Long[] userIds, Long roleId);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [permissionIds, roleId, organizationIds]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    Message setPermission(String[] permissionIds, Long roleId, String[] organizationIds);

    /**
     * @return java.util.List<com.ahsj.sysuserinfo.entity.TreeEntity>
     * @功能说明
     * @Params [sysRole]
     * @Author XJP
     * @Date 2019/9/9
     * @Time 11:12
     **/
    List<TreeEntity> listMenuTree(Role sysRole);
}
