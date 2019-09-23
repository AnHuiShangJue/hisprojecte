package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.Book;

import java.math.BigDecimal;
import java.util.Date;

public class BookDTO extends Book {
    private Long id;

    private Long targetId;

    private Integer bookType;

    private Date startTime;

    private Date endTime;

    private String subscriberName;

    private String phoneNumber;

    private Integer isPay;

    private Integer isCancel;

    private BigDecimal paymentAmount;

    private String remarks;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
    public String getSubscriberName() {
        return subscriberName;
    }

    @Override
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
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
    public Integer getIsPay() {
        return isPay;
    }

    @Override
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
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
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    @Override
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
