package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @program: his
 * @description:挂号统计
 * @author: chenzhicai
 * @create: 2019-06-21-23-47
 **/
public class RegisterdCount extends BaseEntity {
    private BigDecimal totalMoney;
    private int count;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

    