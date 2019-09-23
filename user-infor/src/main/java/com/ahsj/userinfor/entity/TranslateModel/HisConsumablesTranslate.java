package com.ahsj.userinfor.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisConsumablesTranslate
 * <p>
 * Date:     2019/8/1 19:21
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisConsumablesTranslate implements Serializable {

    private static final long serialVersionUID = -4396795569936355211L;
    private Long id;

    private String name;

    private Integer type;

    private String spec;

    private Integer stock;

    private Double price;

    public HisConsumablesTranslate() {
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
