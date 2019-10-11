package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: EnterpriseInfoVO
 * <p>
 * Date:     2019/10/11 16:17
 *
 * @author XJP
 * @create 2019/10/11
 * @since 1.0.0
 */

public class EnterpriseInfoVO  extends EnterpriseInfo {

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
    public String getUnifiedSocialCreditCode() {
        return unifiedSocialCreditCode;
    }

    @Override
    public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
        this.unifiedSocialCreditCode = unifiedSocialCreditCode;
    }

    @Override
    public Short getScale() {
        return scale;
    }

    @Override
    public void setScale(Short scale) {
        this.scale = scale;
    }

    @Override
    public String getEngliseName() {
        return engliseName;
    }

    @Override
    public void setEngliseName(String engliseName) {
        this.engliseName = engliseName;
    }

    @Override
    public String getLegalName() {
        return legalName;
    }

    @Override
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    @Override
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    @Override
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getIsVerify() {
        return isVerify;
    }

    @Override
    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    @Override
    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    @Override
    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    @Override
    public Short getUnitNature() {
        return unitNature;
    }

    @Override
    public void setUnitNature(Short unitNature) {
        this.unitNature = unitNature;
    }

    @Override
    public Date getOperatingPeriodStart() {
        return operatingPeriodStart;
    }

    @Override
    public void setOperatingPeriodStart(Date operatingPeriodStart) {
        this.operatingPeriodStart = operatingPeriodStart;
    }

    @Override
    public Date getOperatingPeriodEnd() {
        return operatingPeriodEnd;
    }

    @Override
    public void setOperatingPeriodEnd(Date operatingPeriodEnd) {
        this.operatingPeriodEnd = operatingPeriodEnd;
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
}
