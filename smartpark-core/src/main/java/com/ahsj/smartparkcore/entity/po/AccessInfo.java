package com.ahsj.smartparkcore.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AccessInfo extends BaseEntity {
    private Long id;

    private Long enterpriseId;

    private String enterpriseName;

    private String accessReason;

    private String accessName;

    private String accessIdcard;

    private String accessPhonenumber;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date accessTime;

    private Long userId;

    private Date updateDate;

    private Date createDate;

    private String remarks;

    private String intervieweeName;

    private String openId;

    private String intervieweePhonenumber;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_verify", typeName = "isVerifyType")
    private Integer isVerify;
    private String isVerifyType;

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public String getIsVerifyType() {
        return isVerifyType;
    }

    public void setIsVerifyType(String isVerifyType) {
        this.isVerifyType = isVerifyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getAccessReason() {
        return accessReason;
    }

    public void setAccessReason(String accessReason) {
        this.accessReason = accessReason == null ? null : accessReason.trim();
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName == null ? null : accessName.trim();
    }

    public String getAccessIdcard() {
        return accessIdcard;
    }

    public void setAccessIdcard(String accessIdcard) {
        this.accessIdcard = accessIdcard == null ? null : accessIdcard.trim();
    }

    public String getAccessPhonenumber() {
        return accessPhonenumber;
    }

    public void setAccessPhonenumber(String accessPhonenumber) {
        this.accessPhonenumber = accessPhonenumber == null ? null : accessPhonenumber.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getIntervieweeName() {
        return intervieweeName;
    }

    public void setIntervieweeName(String intervieweeName) {
        this.intervieweeName = intervieweeName == null ? null : intervieweeName.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getIntervieweePhonenumber() {
        return intervieweePhonenumber;
    }

    public void setIntervieweePhonenumber(String intervieweePhonenumber) {
        this.intervieweePhonenumber = intervieweePhonenumber == null ? null : intervieweePhonenumber.trim();
    }
}