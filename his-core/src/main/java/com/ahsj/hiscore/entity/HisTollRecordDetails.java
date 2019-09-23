package com.ahsj.hiscore.entity;


import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;

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
}
