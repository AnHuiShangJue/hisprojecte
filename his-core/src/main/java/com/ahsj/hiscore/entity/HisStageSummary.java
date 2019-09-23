package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisStageSummary extends BaseEntity {
    private Long id;


    private Date joinDate;

    private Date summaryDate;

    private String admissionSituation;

    private String admissionDiagnosis;

    private String currentDiagnosis;

    private String medicalTreatment;

    private String currentSituation;

    private String medicalPlan;

    private String medicalNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(Date summaryDate) {
        this.summaryDate = summaryDate;
    }

    public String getAdmissionSituation() {
        return admissionSituation;
    }

    public void setAdmissionSituation(String admissionSituation) {
        this.admissionSituation = admissionSituation == null ? null : admissionSituation.trim();
    }

    public String getAdmissionDiagnosis() {
        return admissionDiagnosis;
    }

    public void setAdmissionDiagnosis(String admissionDiagnosis) {
        this.admissionDiagnosis = admissionDiagnosis == null ? null : admissionDiagnosis.trim();
    }

    public String getCurrentDiagnosis() {
        return currentDiagnosis;
    }

    public void setCurrentDiagnosis(String currentDiagnosis) {
        this.currentDiagnosis = currentDiagnosis == null ? null : currentDiagnosis.trim();
    }

    public String getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(String medicalTreatment) {
        this.medicalTreatment = medicalTreatment == null ? null : medicalTreatment.trim();
    }

    public String getCurrentSituation() {
        return currentSituation;
    }

    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation == null ? null : currentSituation.trim();
    }

    public String getMedicalPlan() {
        return medicalPlan;
    }

    public void setMedicalPlan(String medicalPlan) {
        this.medicalPlan = medicalPlan == null ? null : medicalPlan.trim();
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber == null ? null : medicalNumber.trim();
    }
}