package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisConsumablesDestory extends BaseEntity {
    private Long id;

    private Long destoryId;

    private Long consumablesId;

    private String name;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "consumable_type", typeName = "typeName")
    private Integer type;
    private String typeName;


    private String spec;

    private Double price;
    private Double lowPrice;
    private Double highPrice;

    private Integer destoryNumber;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date validityPeriod;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date destoryPeriod;

    private String reason;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "loss_type", typeName = "destoryTypeName")
    private Integer destoryType;
    private String destoryTypeName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_review", typeName = "isReviewName")
    private Integer isReview;
    private String isReviewName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestoryId() {
        return destoryId;
    }

    public void setDestoryId(Long destoryId) {
        this.destoryId = destoryId;
    }

    public Long getConsumablesId() {
        return consumablesId;
    }

    public void setConsumablesId(Long consumablesId) {
        this.consumablesId = consumablesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDestoryNumber() {
        return destoryNumber;
    }

    public void setDestoryNumber(Integer destoryNumber) {
        this.destoryNumber = destoryNumber;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Date getDestoryPeriod() {
        return destoryPeriod;
    }

    public void setDestoryPeriod(Date destoryPeriod) {
        this.destoryPeriod = destoryPeriod;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getDestoryType() {
        return destoryType;
    }

    public void setDestoryType(Integer destoryType) {
        this.destoryType = destoryType;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }


    public String getDestoryTypeName() {
        return destoryTypeName;
    }

    public void setDestoryTypeName(String destoryTypeName) {
        this.destoryTypeName = destoryTypeName;
    }

    public String getIsReviewName() {
        return isReviewName;
    }

    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}