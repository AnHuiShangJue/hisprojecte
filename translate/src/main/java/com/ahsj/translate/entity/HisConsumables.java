package com.ahsj.translate.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

public class HisConsumables extends BaseEntity {
    private Long id;

    private String name;

    private Integer type;

    private String spec;

    private Integer stock;

    private Double price;

    private Double lowPrice;
    private Double highPrice;



    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enable")
    private Integer isEnable;
    private String enable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Integer getIsEnable() {
        return isEnable;
    }


    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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
}