package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class ActivityInfoVO extends ActivityInfo {
    private Long id;

    private String name;

    private String eventLocation;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date registrationDeadline;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date endTime;

    private Long maximumNumber;

    private String description;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_public", typeName = "isPublicName")
    private Integer isPublic;
    private String isPublicName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enableName")
    private Integer  isEnable;
    private String enableName;

    private Integer isReview;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    private String remarks;

    private Long provinceId;

    private Long cityId;

    private Long areaId;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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
    public String getEnableName() {
        return enableName;
    }

    @Override
    public void setEnableName(String enableName) {
        this.enableName = enableName;
    }

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
    public Integer getIsPublic() {
        return isPublic;
    }

    @Override
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsPublicName() {
        return isPublicName;
    }

    public void setIsPublicName(String isPublicName) {
        this.isPublicName = isPublicName;
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
    public Integer getIsReview() {
        return isReview;
    }

    @Override
    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
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
