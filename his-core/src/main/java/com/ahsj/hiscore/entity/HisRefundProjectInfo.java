package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisRefundProjectInfo extends BaseEntity {
    private Long id;

    private String voucher;

    private String medicalRecordNumber;

    private BigDecimal refundSumProce;

    private BigDecimal deposit; //剩余押金

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss ")
    private Date createDate;

    private Date updateDate;

    private Long createUserId;

    private Long updateUserId;

    private Date applicationTime;

    private String remarks;

    private String tollRecordNumber;

    private String patientName;

    private String recordNumber;

    private String vouchers;

    private String userNames;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDate;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDates;

    public Date getConsumablesDetailsCreateDate() {
        return consumablesDetailsCreateDate;
    }

    public void setConsumablesDetailsCreateDate(Date consumablesDetailsCreateDate) {
        this.consumablesDetailsCreateDate = consumablesDetailsCreateDate;
    }

    public Date getConsumablesDetailsCreateDates() {
        return consumablesDetailsCreateDates;
    }

    public void setConsumablesDetailsCreateDates(Date consumablesDetailsCreateDates) {
        this.consumablesDetailsCreateDates = consumablesDetailsCreateDates;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getVouchers() {
        return vouchers;
    }

    public void setVouchers(String vouchers) {
        this.vouchers = vouchers;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTollRecordNumber() {
        return tollRecordNumber;
    }

    public void setTollRecordNumber(String tollRecordNumber) {
        this.tollRecordNumber = tollRecordNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher == null ? null : voucher.trim();
    }

    public BigDecimal getRefundSumProce() {
        return refundSumProce;
    }

    public void setRefundSumProce(BigDecimal refundSumProce) {
        this.refundSumProce = refundSumProce;
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

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    @Override
    public String toString() {
        return "HisRefundProjectInfo{" +
                "id=" + id +
                ", voucher='" + voucher + '\'' +
                ", refundSumProce=" + refundSumProce +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", applicationTime=" + applicationTime +
                ", remarks='" + remarks + '\'' +
                ", tollRecordNumber='" + tollRecordNumber + '\'' +
                '}';
    }
}