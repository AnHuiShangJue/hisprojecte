package com.ahsj.smartparkcore.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Book extends BaseEntity {
    private Long id;

    private Date createDate;

    private Date updateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date endTime;

    private BigDecimal paymentAmount;

    private BigDecimal deposit;

    private String phoneNumber;

    private String subscriberName;

    private Long targetId;

    private String remarks;

    private String description;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "book_type", typeName = "bookTypeName")
    private Integer bookType;
    private String bookTypeName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_audit", typeName = "isAuditName")
    private Integer isAudit;
    private String isAuditName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_cancel", typeName = "isCancelName")
    private Integer isCancel;
    private String isCancelName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPayName")
    private Integer isPay;
    private String isPayName;

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public String getIsAuditName() {
        return isAuditName;
    }

    public void setIsAuditName(String isAuditName) {
        this.isAuditName = isAuditName;
    }

    public String getIsCancelName() {
        return isCancelName;
    }

    public void setIsCancelName(String isCancelName) {
        this.isCancelName = isCancelName;
    }

    public String getIsPayName() {
        return isPayName;
    }

    public void setIsPayName(String isPayName) {
        this.isPayName = isPayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}