package com.ahsj.translate.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisConsumablesBuyplanDetailsTranslate
 * <p>
 * Date:     2019/8/1 19:20
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisConsumablesBuyplanDetailsTranslate implements Serializable {

    private static final long serialVersionUID = -6906317080955694329L;

    private Long id;

    private Long consumablesId;

    private Integer enterCountPlan;

    private String name;

    private String spec;

    private Double price;

    private Long buyplanId;

    public HisConsumablesBuyplanDetailsTranslate() {
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
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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
