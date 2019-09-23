package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisFirstCourseRecord extends BaseEntity {
    private Long id;

    private Long doctorId;

    private Integer isFirstAid;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    private String caseCharacteristics;

    private String proposedConsultation;

    private String medicalPlan;

    private Long recordId;

    private Long hospitalManageId;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_sign",typeName = "isSignName")
    private Integer isSign;
    private String isSignName;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_supplement",typeName = "isSupplementName")
    private Integer isSupplement;
    private String isSupplementName;

    private String recorderName;//记录人姓名  书写医生姓名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getIsFirstAid() {
        return isFirstAid;
    }

    public void setIsFirstAid(Integer isFirstAid) {
        this.isFirstAid = isFirstAid;
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

    public String getCaseCharacteristics() {
        return caseCharacteristics;
    }

    public void setCaseCharacteristics(String caseCharacteristics) {
        this.caseCharacteristics = caseCharacteristics == null ? null : caseCharacteristics.trim();
    }

    public String getProposedConsultation() {
        return proposedConsultation;
    }

    public void setProposedConsultation(String proposedConsultation) {
        this.proposedConsultation = proposedConsultation == null ? null : proposedConsultation.trim();
    }

    public String getMedicalPlan() {
        return medicalPlan;
    }

    public void setMedicalPlan(String medicalPlan) {
        this.medicalPlan = medicalPlan == null ? null : medicalPlan.trim();
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

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public Integer getIsSupplement() {
        return isSupplement;
    }

    public void setIsSupplement(Integer isSupplement) {
        this.isSupplement = isSupplement;
    }

    public String getIsSignName() {
        return isSignName;
    }

    public void setIsSignName(String isSignName) {
        this.isSignName = isSignName;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    public String getIsSupplementName() {
        return isSupplementName;
    }

    public void setIsSupplementName(String isSupplementName) {
        this.isSupplementName = isSupplementName;
    }
}