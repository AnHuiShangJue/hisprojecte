package com.ahsj.hiscore.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisFinanceCondition {
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date leftTime;
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date rightTime;

    public Date getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Date leftTime) {
        this.leftTime = leftTime;
    }

    public Date getRightTime() {
        return rightTime;
    }

    public void setRightTime(Date rightTime) {
        this.rightTime = rightTime;
    }
}
