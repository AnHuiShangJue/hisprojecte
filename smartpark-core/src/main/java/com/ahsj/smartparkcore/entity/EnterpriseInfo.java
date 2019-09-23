package com.ahsj.smartparkcore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业信息
 */
public class EnterpriseInfo extends BaseEntity {
    private Long id;

    private String name;

    private String unifiedSocialCreditCode;

    private Short scale;

    private String engliseName;

    private String legalName;

    private String identificationNumber;

    private String phoneNumber;

    private String description;

    private Integer isVerify;





    private BigDecimal registeredCapital;

    private Short unitNature;

    private Date operatingPeriodStart;

    private Date operatingPeriodEnd;

    private Date createDate;


    private Date updateDate;


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

    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode == null ? null : unifiedSocialCreditCode.trim();
    }

    public Short getScale() {
        return scale;
    }

    public void setScale(Short scale) {
        this.scale = scale;
    }

    public String getEngliseName() {
        return engliseName;
    }

    public void setEngliseName(String engliseName) {
        this.engliseName = engliseName == null ? null : engliseName.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber == null ? null : identificationNumber.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Short getUnitNature() {
        return unitNature;
    }

    public void setUnitNature(Short unitNature) {
        this.unitNature = unitNature;
    }

    public Date getOperatingPeriodStart() {
        return operatingPeriodStart;
    }

    public void setOperatingPeriodStart(Date operatingPeriodStart) {
        this.operatingPeriodStart = operatingPeriodStart;
    }

    public Date getOperatingPeriodEnd() {
        return operatingPeriodEnd;
    }

    public void setOperatingPeriodEnd(Date operatingPeriodEnd) {
        this.operatingPeriodEnd = operatingPeriodEnd;
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

}