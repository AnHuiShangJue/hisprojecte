package com.ahsj.smartparkcore.entity.po;

import core.entity.BaseEntity;

import java.util.Date;

public class Lease  extends BaseEntity {

    private Long id;

    private Long stationInfoId;

    private Long conferenceRoomInfoId;

    private Long siteId;

    private Date updateDate;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationInfoId() {
        return stationInfoId;
    }

    public void setStationInfoId(Long stationInfoId) {
        this.stationInfoId = stationInfoId;
    }

    public Long getConferenceRoomInfoId() {
        return conferenceRoomInfoId;
    }

    public void setConferenceRoomInfoId(Long conferenceRoomInfoId) {
        this.conferenceRoomInfoId = conferenceRoomInfoId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
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