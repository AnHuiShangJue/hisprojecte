package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisSuccession extends BaseEntity {
    private Long id;





    private Date joinDate;

    private String admissionSituation;

    private String admissionDiagnosis;

    private String cuurentDiagnosis;

    private String mediTreatmentProcess;

    private String successionPlanning;

    private Integer isModified;

    private String hospitalNumber;

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

    public String getCuurentDiagnosis() {
        return cuurentDiagnosis;
    }

    public void setCuurentDiagnosis(String cuurentDiagnosis) {
        this.cuurentDiagnosis = cuurentDiagnosis == null ? null : cuurentDiagnosis.trim();
    }

    public String getMediTreatmentProcess() {
        return mediTreatmentProcess;
    }

    public void setMediTreatmentProcess(String mediTreatmentProcess) {
        this.mediTreatmentProcess = mediTreatmentProcess == null ? null : mediTreatmentProcess.trim();
    }

    public String getSuccessionPlanning() {
        return successionPlanning;
    }

    public void setSuccessionPlanning(String successionPlanning) {
        this.successionPlanning = successionPlanning == null ? null : successionPlanning.trim();
    }

    public Integer getIsModified() {
        return isModified;
    }

    public void setIsModified(Integer isModified) {
        this.isModified = isModified;
    }

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber == null ? null : hospitalNumber.trim();
    }
}