package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisDailyRecord extends BaseEntity {
    private Integer id;

    private BigDecimal totalMoney;

    private Date createDate;

    private Long createUserId;

    private Long updateUserId;

    private Date updateDate;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}