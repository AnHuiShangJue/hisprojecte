package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import core.code.CodeValueColumn;
import core.code.Constants;
import lombok.Data;

@Data
public class ActivityPersonnelInfoDTO extends ActivityPersonnelInfo {
    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

    private String phoneNumber;

    private String idcard;

    private String email;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_audit", typeName = "isReviewName")
    private Integer isReview;
    private String isReviewName;

    private Long userId;

    private Long activityId;

    private String openId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String getIsReviewName() {
        return isReviewName;
    }

    @Override
    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
    }
}
