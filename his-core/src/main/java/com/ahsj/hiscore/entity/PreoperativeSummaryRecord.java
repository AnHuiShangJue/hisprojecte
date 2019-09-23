package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import core.entity.BaseEntity;

import java.util.Date;

public class PreoperativeSummaryRecord extends BaseEntity {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Long createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private Long updateUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;

    private String briefCondition;

    private String preoperativeDiagnosis;

    private String surgicalIndication;

    private String prposedSurgeryNamemethod;

    private String smulatedAnesthesia;

    private String precautions;

    private String patientCondition;

    private String medicalNumber;

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

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getBriefCondition() {
        return briefCondition;
    }

    public void setBriefCondition(String briefCondition) {
        this.briefCondition = briefCondition == null ? null : briefCondition.trim();
    }

    public String getPreoperativeDiagnosis() {
        return preoperativeDiagnosis;
    }

    public void setPreoperativeDiagnosis(String preoperativeDiagnosis) {
        this.preoperativeDiagnosis = preoperativeDiagnosis == null ? null : preoperativeDiagnosis.trim();
    }

    public String getSurgicalIndication() {
        return surgicalIndication;
    }

    public void setSurgicalIndication(String surgicalIndication) {
        this.surgicalIndication = surgicalIndication == null ? null : surgicalIndication.trim();
    }

    public String getPrposedSurgeryNamemethod() {
        return prposedSurgeryNamemethod;
    }

    public void setPrposedSurgeryNamemethod(String prposedSurgeryNamemethod) {
        this.prposedSurgeryNamemethod = prposedSurgeryNamemethod == null ? null : prposedSurgeryNamemethod.trim();
    }

    public String getSmulatedAnesthesia() {
        return smulatedAnesthesia;
    }

    public void setSmulatedAnesthesia(String smulatedAnesthesia) {
        this.smulatedAnesthesia = smulatedAnesthesia == null ? null : smulatedAnesthesia.trim();
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions == null ? null : precautions.trim();
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition == null ? null : patientCondition.trim();
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber == null ? null : medicalNumber.trim();
    }
}