package com.ahsj.smartparkcore.entity.po;

import core.entity.BaseEntity;

import java.util.Date;

public class Region extends BaseEntity {
    private Long id;

    private String name;

    private Long parentId;

    private Long conferenceRoomInfoId;

    private Long stationInfoId;

    private Long siteId;

    private Long enterpriseId;

    private Date updateDate;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getConferenceRoomInfoId() {
        return conferenceRoomInfoId;
    }

    public void setConferenceRoomInfoId(Long conferenceRoomInfoId) {
        this.conferenceRoomInfoId = conferenceRoomInfoId;
    }

    public Long getStationInfoId() {
        return stationInfoId;
    }

    public void setStationInfoId(Long stationInfoId) {
        this.stationInfoId = stationInfoId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}