package com.ahsj.translate.entity;

import core.entity.BaseEntity;

import java.io.Serializable;

public class HisConsumablesBuyplanDetails extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8805675523866950037L;
    private Long id;

    private Long consumablesId;

    private Integer enterCountPlan;

    private String name;

    private String spec;

    private Double price;

    private Long buyplanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsumablesId() {
        return consumablesId;
    }

    public void setConsumablesId(Long consumablesId) {
        this.consumablesId = consumablesId;
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


    public Long getBuyplanId() {
        return buyplanId;
    }

    public void setBuyplanId(Long buyplanId) {
        this.buyplanId = buyplanId;
    }
}