package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisInpatientMedicalRecord extends BaseEntity {
    private Long id;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "medical_record_type", typeName = "medicalRecordTypeName")
    private Integer medicalRecordType;
    private String medicalRecordTypeName;

    private Long targetId;

    private Long recordId;

    private Long hospitalManageId;

    private Integer mustSignNumber;

    private Integer actualSignNumber;

    private String signName;

    private String operatorName;//操作人姓名

    private String hospitalManageNumber;//住院编号

    private Integer isOperate;//是否可签字和修改

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getHospitalManageNumber() {
        return hospitalManageNumber;
    }

    public void setHospitalManageNumber(String hospitalManageNumber) {
        this.hospitalManageNumber = hospitalManageNumber;
    }

    public Integer getIsOperate() {
        return isOperate;
    }

    public void setIsOperate(Integer isOperate) {
        this.isOperate = isOperate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getMedicalRecordType() {
        return medicalRecordType;
    }

    public void setMedicalRecordType(Integer medicalRecordType) {
        this.medicalRecordType = medicalRecordType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getHospitalManageId() {
        return hospitalManageId;
    }

    public void setHospitalManageId(Long hospitalManageId) {
        this.hospitalManageId = hospitalManageId;
    }

    public Integer getMustSignNumber() {
        return mustSignNumber;
    }

    public void setMustSignNumber(Integer mustSignNumber) {
        this.mustSignNumber = mustSignNumber;
    }

    public Integer getActualSignNumber() {
        return actualSignNumber;
    }

    public void setActualSignNumber(Integer actualSignNumber) {
        this.actualSignNumber = actualSignNumber;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    public String getMedicalRecordTypeName() {
        return medicalRecordTypeName;
    }

    public void setMedicalRecordTypeName(String medicalRecordTypeName) {
        this.medicalRecordTypeName = medicalRecordTypeName;
    }
}