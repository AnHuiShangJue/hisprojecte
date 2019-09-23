package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisRelatedMedicationandexit extends BaseEntity {
    private Long id;

    private Long medicationDetailId;

    private Long exitId;

    private Integer count;

    private Date createDate;

    private Date updateDate;

    private String drugsNumb;

    private Date validityPeriod;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicationDetailId() {
        return medicationDetailId;
    }

    public void setMedicationDetailId(Long medicationDetailId) {
        this.medicationDetailId = medicationDetailId;
    }

    public Long getExitId() {
        return exitId;
    }

    public void setExitId(Long exitId) {
        this.exitId = exitId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb == null ? null : drugsNumb.trim();
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}