package com.ahsj.baseuserinfo.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class OrgMenu extends BaseEntity {
    private Long id;

    private Long orgId;

    private Long menuId;

    private Long[] ids;

    private String permssionPermsId;

    private Long createId;

    private Date createTime;

    private Long updateId;

    private Date updateTime;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getPermssionPermsId() {
        return permssionPermsId;
    }

    public void setPermssionPermsId(String permssionPermsId) {
        this.permssionPermsId = permssionPermsId == null ? null : permssionPermsId.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}