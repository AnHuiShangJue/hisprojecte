package com.ahsj.smartparkcore.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业信息
 */


public class EnterpriseInfo extends BaseEntity {

    private Long id;

    private String name;

    private String unifiedSocialCreditCode;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_company_size", typeName = "scaleName")
    private Short scale;
    private String scaleName;

    private String engliseName;

    private String legalName;

    private String identificationNumber;

    private String phoneNumber;

    private String description;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_verify", typeName = "isVerifyType")
    private Integer isVerify;
    private String isVerifyType;

    private BigDecimal registeredCapital;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "company_type", typeName = "companyTypeName")
    private Short unitNature;  //company_type
    private String companyTypeName;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date operatingPeriodStart;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date operatingPeriodEnd;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    private String phone;

    private String idCard;

    private String landlineNumber;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enableName")
    private Integer isEnable;
    private String enableName;

    private String address;

    public String getCompanyTypeName() {
        return companyTypeName;
    }

    public void setCompanyTypeName(String companyTypeName) {
        this.companyTypeName = companyTypeName;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }
}