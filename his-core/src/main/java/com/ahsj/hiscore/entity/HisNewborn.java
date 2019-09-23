package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisNewborn extends BaseEntity {
    private Long id;

    private Long createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Long updateUserId;

    private Integer activity;

    private Integer pulse;

    private Integer respiration;

    private String bornName;

    private Integer bedNumber;

    private String parentAddress;

    private String bornAddress;

    private Integer apprarance;

    private String national;

    private String idCard;

    private String pName;

    private BigDecimal headCircumference;

    private String healthStandards;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer bornSex;
    private String sexName;

    private BigDecimal weight;

    private BigDecimal height;

    private Integer grimaceResponse;

    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "isEnableType")//is_enable  disable
    private Integer isEnable;
    private String isEnableType;

    private String remark;

    private Integer hidden;

    private Integer param;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lowCreateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date highCreateDate;

    public Date getLowCreateDate() {
        return lowCreateDate;
    }

    public void setLowCreateDate(Date lowCreateDate) {
        this.lowCreateDate = lowCreateDate;
    }

    public Date getHighCreateDate() {
        return highCreateDate;
    }

    public void setHighCreateDate(Date highCreateDate) {
        this.highCreateDate = highCreateDate;
    }

    public Integer getParam() {
        return param;
    }

    public void setParam(Integer param) {
        this.param = param;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getRespiration() {
        return respiration;
    }

    public void setRespiration(Integer respiration) {
        this.respiration = respiration;
    }

    public String getBornName() {
        return bornName;
    }

    public void setBornName(String bornName) {
        this.bornName = bornName == null ? null : bornName.trim();
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress == null ? null : parentAddress.trim();
    }

    public String getBornAddress() {
        return bornAddress;
    }

    public void setBornAddress(String bornAddress) {
        this.bornAddress = bornAddress == null ? null : bornAddress.trim();
    }

    public Integer getApprarance() {
        return apprarance;
    }

    public void setApprarance(Integer apprarance) {
        this.apprarance = apprarance;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national == null ? null : national.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    public BigDecimal getHeadCircumference() {
        return headCircumference;
    }

    public void setHeadCircumference(BigDecimal headCircumference) {
        this.headCircumference = headCircumference;
    }

    public String getHealthStandards() {
        return healthStandards;
    }

    public void setHealthStandards(String healthStandards) {
        this.healthStandards = healthStandards == null ? null : healthStandards.trim();
    }

    public Integer getBornSex() {
        return bornSex;
    }

    public void setBornSex(Integer bornSex) {
        this.bornSex = bornSex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Integer getGrimaceResponse() {
        return grimaceResponse;
    }

    public void setGrimaceResponse(Integer grimaceResponse) {
        this.grimaceResponse = grimaceResponse;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsEnableType() {
        return isEnableType;
    }

    public void setIsEnableType(String isEnableType) {
        this.isEnableType = isEnableType;
    }
}