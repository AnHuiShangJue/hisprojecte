package com.ahsj.hiscore.entity;

import java.math.BigDecimal;
import java.util.Date;

public class HisVisitCardRecord {
    private Long id;

    private Long visitCardId;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private Long updateUserId;

    private BigDecimal amount;

    private Long tollRecordId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitCardId() {
        return visitCardId;
    }

    public void setVisitCardId(Long visitCardId) {
        this.visitCardId = visitCardId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTollRecordId() {
        return tollRecordId;
    }

    public void setTollRecordId(Long tollRecordId) {
        this.tollRecordId = tollRecordId;
    }
}