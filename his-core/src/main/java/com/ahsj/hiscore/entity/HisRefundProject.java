package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisRefundProject extends BaseEntity {
    private Long id;

    private String recordNumber;

    private String voucher;

    private String patientName;

    private Integer refundNum;

    private Long recordProjectId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_back", typeName = "isBackName")
    private Integer isBack;
    private String isBackName;

    private String remarks;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_review", typeName = "isReviewName")
    private Integer isReview;
    private  String isReviewName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDate;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDates;

    private String number ;

    private String tollRecordNumber;

    public String getTollRecordNumber() {
        return tollRecordNumber;
    }

    public void setTollRecordNumber(String tollRecordNumber) {
        this.tollRecordNumber = tollRecordNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getIsReviewName() {
        return isReviewName;
    }

    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getIsBackName() {
        return isBackName;
    }

    public void setIsBackName(String isBackName) {
        this.isBackName = isBackName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber == null ? null : recordNumber.trim();
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher == null ? null : voucher.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Long getRecordProjectId() {
        return recordProjectId;
    }

    public void setRecordProjectId(Long recordProjectId) {
        this.recordProjectId = recordProjectId;
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

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String toString() {
        return "HisRefundProject{" +
                "id=" + id +
                ", recordNumber='" + recordNumber + '\'' +
                ", voucher='" + voucher + '\'' +
                ", patientName='" + patientName + '\'' +
                ", refundNum=" + refundNum +
                ", recordProjectId=" + recordProjectId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isBack=" + isBack +
                ", isBackName='" + isBackName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isReview=" + isReview +
                ", isReviewName='" + isReviewName + '\'' +
                ", applicationTime=" + applicationTime +
                ", consumablesDetailsCreateDate=" + consumablesDetailsCreateDate +
                ", consumablesDetailsCreateDates=" + consumablesDetailsCreateDates +
                ", number='" + number + '\'' +
                ", tollRecordNumber='" + tollRecordNumber + '\'' +
                '}';
    }
}