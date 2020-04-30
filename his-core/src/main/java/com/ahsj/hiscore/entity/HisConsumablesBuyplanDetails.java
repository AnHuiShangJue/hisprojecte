package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;


public class HisConsumablesBuyplanDetails extends BaseEntity {

    private Long id;

    private String buyplanCode;

    private String consumablesCode;

    private Integer enterCountPlan;

    private String name;

    private String spec;

    private Double price;

    private Date createDate;

    private Date updateDate;

    private Integer isDelete;

    public String getBuyplanCode() {
        return buyplanCode;
    }

    public void setBuyplanCode(String buyplanCode) {
        this.buyplanCode = buyplanCode;
    }

    public String getConsumablesCode() {
        return consumablesCode;
    }

    public void setConsumablesCode(String consumablesCode) {
        this.consumablesCode = consumablesCode;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getEnterCountPlan() {
        return enterCountPlan;
    }

    public void setEnterCountPlan(Integer enterCountPlan) {
        this.enterCountPlan = enterCountPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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


}