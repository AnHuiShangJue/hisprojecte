package com.ahsj.sysuserinfo.entity;

import core.entity.BaseEntity;

public class UserOrganization extends BaseEntity {

    private Long id;

    private Long userId;

    private String deptId;

    private String isLeader;

    private Long[] ids;

    private String isInitData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader == null ? null : isLeader.trim();
    }

    public String getIsInitData() {
        return isInitData;
    }

    public void setIsInitData(String isInitData) {
        this.isInitData = isInitData == null ? null : isInitData.trim();
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}