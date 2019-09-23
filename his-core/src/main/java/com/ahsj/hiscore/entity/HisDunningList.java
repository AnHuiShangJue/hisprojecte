package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;

public class HisDunningList extends BaseEntity {
    private Long id;

    private String number;

    private BigDecimal money;

    private String hospiManagerNumber;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getHospiManagerNumber() {
        return hospiManagerNumber;
    }

    public void setHospiManagerNumber(String hospiManagerNumber) {
        this.hospiManagerNumber = hospiManagerNumber == null ? null : hospiManagerNumber.trim();
    }


}