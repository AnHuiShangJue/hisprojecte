package com.ahsj.hiseurekasecurityauthorizationserverone.entity;

import core.entity.BaseEntity;

/**
 * @program: his
 * @description:MenuOperate
 * @author: chenzhicai
 * @create: 2019-07-01-15-37
 **/
public class MenuOperate extends BaseEntity {
    private Long id;

    private Long menuId;

    private Long operateId;

    private String permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}
    