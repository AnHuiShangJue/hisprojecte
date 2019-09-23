package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisHandover extends BaseEntity {
    private Long id;


    private String createUserName;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;

    private String admissionSituation;

    private String admissionDiagnosis;

    private String cuurentDiagnosis;

    private String mediTreatmentProcess;

    private String handoverConsiderations;

    private Integer isModified;

    private String hospitalNumber;

    private Long successionUserId;

    private String successionUserName;

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

    public String getHandoverConsiderations() {
        return handoverConsiderations;
    }

    public void setHandoverConsiderations(String handoverConsiderations) {
        this.handoverConsiderations = handoverConsiderations == null ? null : handoverConsiderations.trim();
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

    public Long getSuccessionUserId() {
        return successionUserId;
    }

    public void setSuccessionUserId(Long successionUserId) {
        this.successionUserId = successionUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getSuccessionUserName() {
        return successionUserName;
    }

    public void setSuccessionUserName(String successionUserName) {
        this.successionUserName = successionUserName;
    }
}