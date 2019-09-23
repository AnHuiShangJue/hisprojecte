package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisHospitalConsumablesDetails extends BaseEntity {
    private Long id;

    private Long hisHospitalManagerId;

    private Long hisConsumablesDetailsId;

    private Date createDate;

    private Date updateDate;

    private Integer comsumablesNum;

    private Integer isOutbound;

    private String consumableNumber;

    private String medicalRecordNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationTime;

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getConsumableNumber() {
        return consumableNumber;
    }

    public void setConsumableNumber(String consumableNumber) {
        this.consumableNumber = consumableNumber;
    }


    public Integer getIsOutbound() {
        return isOutbound;
    }

    public void setIsOutbound(Integer isOutbound) {
        this.isOutbound = isOutbound;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHisHospitalManagerId() {
        return hisHospitalManagerId;
    }

    public void setHisHospitalManagerId(Long hisHospitalManagerId) {
        this.hisHospitalManagerId = hisHospitalManagerId;
    }

    public Long getHisConsumablesDetailsId() {
        return hisConsumablesDetailsId;
    }

    public void setHisConsumablesDetailsId(Long hisConsumablesDetailsId) {
        this.hisConsumablesDetailsId = hisConsumablesDetailsId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getComsumablesNum() {
        return comsumablesNum;
    }

    public void setComsumablesNum(Integer comsumablesNum) {
        this.comsumablesNum = comsumablesNum;
    }

    @Override
    public String toString() {
        return "HisHospitalConsumablesDetails{" +
                "id=" + id +
                ", hisHospitalManagerId=" + hisHospitalManagerId +
                ", hisConsumablesDetailsId=" + hisConsumablesDetailsId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", comsumablesNum=" + comsumablesNum +
                ", isOutbound=" + isOutbound +
                ", consumableNumber='" + consumableNumber + '\'' +
                ", medicalRecordNumber='" + medicalRecordNumber + '\'' +
                '}';
    }
}