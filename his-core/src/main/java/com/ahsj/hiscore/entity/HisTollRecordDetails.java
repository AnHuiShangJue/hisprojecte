package com.ahsj.hiscore.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisTollRecordDetails extends BaseEntity {
  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    String patientName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexName")
    Integer sex;
    String sexName;

    String departmentName;

    String doctorName;

    BigDecimal restDeposit;

    BigDecimal depositWarning;

    BigDecimal money;

    String name;

    Integer num;

    BigDecimal price;

    BigDecimal total;

    @DateTimeFormat(pattern="MM-dd-yyyy ")
    @JsonFormat(pattern="MM-dd-yyyy ",timezone = "GMT+8")
    Date createDate;

    private Integer isBack;
    private String isBackName;

    private Integer alreadyBack;


    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public String getIsBackName() {
        return isBackName;
    }

    public void setIsBackName(String isBackName) {
        this.isBackName = isBackName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    public BigDecimal getRestDeposit() {
        return restDeposit;
    }

    public void setRestDeposit(BigDecimal restDeposit) {
        this.restDeposit = restDeposit;
    }

    public BigDecimal getDepositWarning() {
        return depositWarning;
    }

    public void setDepositWarning(BigDecimal depositWarning) {
        this.depositWarning = depositWarning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAlreadyBack() {
        return alreadyBack;
    }

    public void setAlreadyBack(Integer alreadyBack) {
        this.alreadyBack = alreadyBack;
    }
}
