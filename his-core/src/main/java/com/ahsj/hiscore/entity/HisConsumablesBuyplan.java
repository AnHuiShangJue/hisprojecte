package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisConsumablesBuyplan extends BaseEntity {
    private Long id;

    private Long buyplanId;

    private String personInCharge;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date expectedTime;
    private Date lowexpectedTime;
    private Date highexpectedTime;

    private Double budget;
    private Double lowBudget;
    private Double highBudget;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "completion",typeName = "completionName")
    private Integer completion;
    private String completionName;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date completionTime;

    private BigDecimal actualExpenditure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyplanId() {
        return buyplanId;
    }

    public void setBuyplanId(Long buyplanId) {
        this.buyplanId = buyplanId;
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getLowBudget() {
        return lowBudget;
    }

    public void setLowBudget(Double lowBudget) {
        this.lowBudget = lowBudget;
    }

    public Double getHighBudget() {
        return highBudget;
    }

    public void setHighBudget(Double highBudget) {
        this.highBudget = highBudget;
    }

    public String getCompletionName() {
        return completionName;
    }

    public void setCompletionName(String completionName) {
        this.completionName = completionName;
    }
}