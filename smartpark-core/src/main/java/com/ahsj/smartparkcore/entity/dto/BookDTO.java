package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.Book;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class BookDTO extends Book {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "book_type", typeName = "bookTypeName")
    private Integer bookType;
    private String bookTypeName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_audit", typeName = "isAuditName")
    private Integer isAudit;
    private String isAuditName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_cancel", typeName = "isCancelName")
    private Integer isCancel;
    private String isCancelName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPayName")
    private Integer isPay;
    private String isPayName;

    private BigDecimal paymentAmount;

    private BigDecimal deposit;

    private String phoneNumber;

    private String subscriberName;

    private Long targetId;

    private String remarks;

    private String description;

    private Double area;

    private String capacity;


    private String location;

    private String name;

    private BigDecimal price;

    private String contactName;



    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

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

    public String getIsAuditName() {
        return isAuditName;
    }

    public void setIsAuditName(String isAuditName) {
        this.isAuditName = isAuditName;
    }

    @Override
    public Integer getIsCancel() {
        return isCancel;
    }

    @Override
    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public String getIsCancelName() {
        return isCancelName;
    }

    public void setIsCancelName(String isCancelName) {
        this.isCancelName = isCancelName;
    }

    @Override
    public Integer getIsPay() {
        return isPay;
    }

    @Override
    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getIsPayName() {
        return isPayName;
    }

    public void setIsPayName(String isPayName) {
        this.isPayName = isPayName;
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

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }
}
