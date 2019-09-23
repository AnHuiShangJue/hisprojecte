package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class MedicinePurchasingPlan extends BaseEntity {
    private Long id;

    private Long pharmacyId;

    private Long enterCountPlan;

    private String drugsNumb;

    private String drugsName;

    private String drugsSpec;

    private BigDecimal price;

    private String planNumber;


    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getEnterCountPlan() {
        return enterCountPlan;
    }

    public void setEnterCountPlan(Long enterCountPlan) {
        this.enterCountPlan = enterCountPlan;
    }

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb == null ? null : drugsNumb.trim();
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName == null ? null : drugsName.trim();
    }

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec == null ? null : drugsSpec.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}