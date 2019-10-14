package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityInfoDTO extends ActivityInfo {

    private Long id;

    private String name;

    private String eventLocation;

    private Date registrationDeadline;

    private Date startTime;

    private Date endTime;

    private Long maximumNumber;

    private String description;

    private Integer isEnable;

    private Date createDate;

    private Date updateDate;

    private Long enterpriseId;

    private Long userId;

    private Integer isPublic;

    private String remarks;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEventLocation() {
        return eventLocation;
    }

    @Override
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    @Override
    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public Long getMaximumNumber() {
        return maximumNumber;
    }

    @Override
    public void setMaximumNumber(Long maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    @Override
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Integer getIsPublic() {
        return isPublic;
    }

    @Override
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public Integer getIsEnable() {
        return isEnable;
    }

    @Override
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
