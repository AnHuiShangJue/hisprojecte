package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisMedicinePurchasingPlanRecord extends BaseEntity{
    private Long id;

    private String planNumber;

    private String personInCharge;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date expectedTime;
    private Date lowexpectedTime;
    private Date highexpectedTime;

    private BigDecimal budget;
    private BigDecimal lowBudget;
    private BigDecimal highBudget;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "completion",typeName = "completionName")
    private Integer completion;
    private String completionName;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date completionTime;

    private BigDecimal actualExpenditure;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;
    private Date lowCreateDate;
    private Date highCreateDate;

    public Date getLowCreateDate() {
        return lowCreateDate;
    }

    public void setLowCreateDate(Date lowCreateDate) {
        this.lowCreateDate = lowCreateDate;
    }

    public Date getHighCreateDate() {
        return highCreateDate;
    }

    public void setHighCreateDate(Date highCreateDate) {
        this.highCreateDate = highCreateDate;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCompletionName() {
        return completionName;
    }

    public void setCompletionName(String completionName) {
        this.completionName = completionName;
    }

    public BigDecimal getLowBudget() {
        return lowBudget;
    }

    public void setLowBudget(BigDecimal lowBudget) {
        this.lowBudget = lowBudget;
    }

    public BigDecimal getHighBudget() {
        return highBudget;
    }

    public void setHighBudget(BigDecimal highBudget) {
        this.highBudget = highBudget;
    }

    public Date getLowexpectedTime() {
        return lowexpectedTime;
    }

    public void setLowexpectedTime(Date lowexpectedTime) {
        this.lowexpectedTime = lowexpectedTime;
    }

    public Date getHighexpectedTime() {
        return highexpectedTime;
    }

    public void setHighexpectedTime(Date highexpectedTime) {
        this.highexpectedTime = highexpectedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge == null ? null : personInCharge.trim();
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public BigDecimal getActualExpenditure() {
        return actualExpenditure;
    }

    public void setActualExpenditure(BigDecimal actualExpenditure) {
        this.actualExpenditure = actualExpenditure;
    }

}