package com.ahsj.smartparkcore.entity.po;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class ActivityInfo extends BaseEntity {
    private Long id;

    private String name;

    private String eventLocation;

    private Date registrationDeadline;

    private Date startTime;

    private Date endTime;

    private Long maximumNumber;

    private String description;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enableName")
    private Integer  isEnable;
    private String enableName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_audit", typeName = "isReviewName")
    private Integer isReview;
    private String isReviewName;

    private Date createDate;

    private Date updateDate;

    private Long enterpriseId;

    private Long userId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_public", typeName = "isPublicName")
    private Integer isPublic;
    private String isPublicName;

    private String remarks;

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getEnableName() {
        return enableName;
    }

    public void setEnableName(String enableName) {
        this.enableName = enableName;
    }

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

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation == null ? null : eventLocation.trim();
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getMaximumNumber() {
        return maximumNumber;
    }

    public void setMaximumNumber(Long maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsPublicName() {
        return isPublicName;
    }

    public void setIsPublicName(String isPublicName) {
        this.isPublicName = isPublicName;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsReviewName() {
        return isReviewName;
    }

    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
    }
}