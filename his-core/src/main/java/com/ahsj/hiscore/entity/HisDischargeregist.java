package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisDischargeregist extends BaseEntity {
    private Long id;

    private Long patientId;

    private String number;

    private Date outtime;

    private Date jointime;

    private String dischargeDiagnosis;

    private String admissionSituation;

    private String treatment;

    private String dischargeInstructions;

    private String treatmentEffect;

    private String medicalNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public String getDischargeDiagnosis() {
        return dischargeDiagnosis;
    }

    public void setDischargeDiagnosis(String dischargeDiagnosis) {
        this.dischargeDiagnosis = dischargeDiagnosis;
    }

    public String getAdmissionSituation() {
        return admissionSituation;
    }

    public void setAdmissionSituation(String admissionSituation) {
        this.admissionSituation = admissionSituation;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDischargeInstructions() {
        return dischargeInstructions;
    }

    public void setDischargeInstructions(String dischargeInstructions) {
        this.dischargeInstructions = dischargeInstructions;
    }

    public String getTreatmentEffect() {
        return treatmentEffect;
    }

    public void setTreatmentEffect(String treatmentEffect) {
        this.treatmentEffect = treatmentEffect;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }
}