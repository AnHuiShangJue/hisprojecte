package com.ahsj.hiscore.entity.TranslateModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisConsumablesDetailsTranslate
 * <p>
 * Date:     2019/8/1 19:21
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisConsumablesDetailsTranslate implements Serializable {

    private static final long serialVersionUID = 3496549210211684478L;
    private Long id;

    private Long consumablesId;

    private String name;

    private Integer type;

    private String spec;

    private Integer stock;

    private Integer stockWarn;

    private Double price;

    private Double lowPrice;

    private Double highPrice;

    public HisConsumablesDetailsTranslate() {
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.spec = spec;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockWarn() {
        return stockWarn;
    }

    public void setStockWarn(Integer stockWarn) {
        this.stockWarn = stockWarn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
