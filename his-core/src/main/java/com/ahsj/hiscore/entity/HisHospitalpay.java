package com.ahsj.hiscore.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HisHospitalpay {
    private Long id;

    private BigDecimal amountReceivable;

    private BigDecimal paidAmount;

    private BigDecimal retrieveAmount;

    private Long hosptalRegistId;

    private BigDecimal deposit;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(BigDecimal amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getRetrieveAmount() {
        return retrieveAmount;
    }

    public void setRetrieveAmount(BigDecimal retrieveAmount) {
        this.retrieveAmount = retrieveAmount;
    }

    public Long getHosptalRegistId() {
        return hosptalRegistId;
    }

    public void setHosptalRegistId(Long hosptalRegistId) {
        this.hosptalRegistId = hosptalRegistId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}