package com.ahsj.baseuserinfo.entity;

import core.entity.BaseEntity;

public class RolePermission extends BaseEntity {
    private Long id;

    private Long roleId;

    private String menuTreeId;

    private String operateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getMenuTreeId() {
        return menuTreeId;
    }

    public void setMenuTreeId(String menuTreeId) {
        this.menuTreeId = menuTreeId == null ? null : menuTreeId.trim();
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId == null ? null : operateId.trim();
    }
}