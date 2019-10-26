package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.Book;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


public class BookVO extends Book {

    private Long id;

    private Date createDate;

    private Date updateDate;

    private Integer bookType;

    private Date startTime;

    private Date endTime;

    private Integer isAudit;

    private Integer isCancel;

    private Integer isPay;

    private BigDecimal paymentAmount;

    private BigDecimal deposit;

    private String phoneNumber;

    private String subscriberName;

    private Long targetId;

    private String remarks;

    private String description;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    @Override
    public Integer getBookType() {
        return bookType;
    }

    @Override
    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public Integer getIsAudit() {
        return isAudit;
    }

    @Override
    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    @Override
    public Integer getIsCancel() {
        return isCancel;
    }

    @Override
    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    @Override
    public Integer getIsPay() {
        return isPay;
    }

    @Override
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    @Override
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    @Override
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public BigDecimal getDeposit() {
        return deposit;
    }

    @Override
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
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
    public String getSubscriberName() {
        return subscriberName;
    }

    @Override
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    @Override
    public Long getTargetId() {
        return targetId;
    }

    @Override
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
