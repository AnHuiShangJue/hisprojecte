package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisRegisteredType extends BaseEntity {
    private Long id;

    private Long registerId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "department_type", typeName = "typeIdName")
    private Integer typeId;
    private String typeIdName;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "orgName")
    private Integer orgId;
    private String orgName;


    private BigDecimal money;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "pay_type", typeName = "moneyTypeName")
    private Integer moneyType;
    private String moneyTypeName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "scheduling_type", typeName = "timeTypeName")
    private Integer timeType;
    private String timeTypeName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }


    public String getTimeTypeName() {
        return timeTypeName;
    }

    public void setTimeTypeName(String timeTypeName) {
        this.timeTypeName = timeTypeName;
    }

    public String getTypeIdName() {
        return typeIdName;
    }

    public void setTypeIdName(String typeIdName) {
        this.typeIdName = typeIdName;
    }


    public String getMoneyTypeName() {
        return moneyTypeName;
    }

    public void setMoneyTypeName(String moneyTypeName) {
        this.moneyTypeName = moneyTypeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "HisRegisteredType{" +
                "id=" + id +
                ", registerId=" + registerId +
                ", typeId=" + typeId +
                ", typeIdName='" + typeIdName + '\'' +
                ", orgId=" + orgId +
                ", orgName='" + orgName + '\'' +
                ", money=" + money +
                ", moneyType=" + moneyType +
                ", moneyTypeName='" + moneyTypeName + '\'' +
                ", timeType=" + timeType +
                ", timeTypeName='" + timeTypeName + '\'' +
                '}';
    }
}
