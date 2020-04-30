package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisRefundConsumables  extends BaseEntity implements Serializable {
    private Long id;

    private String recordNumber;

    private String voucher;

    private String patientName;

    private Integer refundNum;

    private BigDecimal refundSumProce;

    private String recordConsumablesCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date updateDate;

    private Date applicationTime;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_back", typeName = "isBackName")
    private Short isBack;
    private String isBackName;

    private String remarks;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_review", typeName = "isReviewName")
    private Integer isReview;
    private  String isReviewName;

    private String tollRecordNumber;

    private Integer isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDate;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDates;

    public BigDecimal getRefundSumProce() {
        return refundSumProce;
    }

    public void setRefundSumProce(BigDecimal refundSumProce) {
        this.refundSumProce = refundSumProce;
    }

    private static final long serialVersionUID = 1L;

    public String getIsReviewName() {
        return isReviewName;
    }

    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
    }

    public String getIsBackName() {
        return isBackName;
    }

    public void setIsBackName(String isBackName) {
        this.isBackName = isBackName;
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

    public String getRecordConsumablesCode() {
        return recordConsumablesCode;
    }

    public void setRecordConsumablesCode(String recordConsumablesCode) {
        this.recordConsumablesCode = recordConsumablesCode == null ? null : recordConsumablesCode.trim();
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Short getIsBack() {
        return isBack;
    }

    public void setIsBack(Short isBack) {
        this.isBack = isBack;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getTollRecordNumber() {
        return tollRecordNumber;
    }

    public void setTollRecordNumber(String tollRecordNumber) {
        this.tollRecordNumber = tollRecordNumber == null ? null : tollRecordNumber.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", recordNumber=").append(recordNumber);
        sb.append(", voucher=").append(voucher);
        sb.append(", patientName=").append(patientName);
        sb.append(", refundNum=").append(refundNum);
        sb.append(", recordConsumablesCode=").append(recordConsumablesCode);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", applicationTime=").append(applicationTime);
        sb.append(", isBack=").append(isBack);
        sb.append(", remarks=").append(remarks);
        sb.append(", isReview=").append(isReview);
        sb.append(", tollRecordNumber=").append(tollRecordNumber);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}