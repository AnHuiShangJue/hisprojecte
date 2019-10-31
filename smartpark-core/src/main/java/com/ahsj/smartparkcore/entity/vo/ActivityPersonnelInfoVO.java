package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ActivityPersonnelInfoVO extends ActivityPersonnelInfo {
    private Long id;

    private String name;

    private Integer age;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexName")
    private Integer sex;
    private String sexName;

    private String phoneNumber;

    private String idcard;

    private String email;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_audit", typeName = "isReviewName")
    private Integer isReview;
    private String isReviewName;

    private Long userId;

    private Long activityId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    private String remarks;

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Integer getSex() {
        return sex;
    }

    @Override
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getIdcard() {
        return idcard;
    }

    @Override
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getActivityId() {
        return activityId;
    }

    @Override
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getIsReviewName() {
        return isReviewName;
    }

    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
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
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
}
