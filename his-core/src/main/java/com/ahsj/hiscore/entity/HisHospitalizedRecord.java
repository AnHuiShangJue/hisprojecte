package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisHospitalizedRecord extends BaseEntity {
    private Long id;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    private Long count;

    private String name;

    private String job;

    private Integer sex;

    private String workUnit;

    private Integer age;

    private String address;

    private Integer isMarried;

    private String forHistory;

    private String reliabilityLevel;

    private String birthplace;

    private Date admissionDate;

    private String nation;

    private Date recordDate;

    private String complaint;

    private String historyPresentIllness;

    private String personalHistory;

    private String maritalHistory;

    private String familyHistory;

    private String healthCheckup;

    private String specialityCheckup;

    private String auxiliaryExamination;

    private String tentativeDiagnosis;

    private String doctorId;

    private Date doctorSign;

    private String idcard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
    }

    public String getForHistory() {
        return forHistory;
    }

    public void setForHistory(String forHistory) {
        this.forHistory = forHistory == null ? null : forHistory.trim();
    }

    public String getReliabilityLevel() {
        return reliabilityLevel;
    }

    public void setReliabilityLevel(String reliabilityLevel) {
        this.reliabilityLevel = reliabilityLevel == null ? null : reliabilityLevel.trim();
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint == null ? null : complaint.trim();
    }

    public String getHistoryPresentIllness() {
        return historyPresentIllness;
    }

    public void setHistoryPresentIllness(String historyPresentIllness) {
        this.historyPresentIllness = historyPresentIllness == null ? null : historyPresentIllness.trim();
    }

    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory == null ? null : personalHistory.trim();
    }

    public String getMaritalHistory() {
        return maritalHistory;
    }

    public void setMaritalHistory(String maritalHistory) {
        this.maritalHistory = maritalHistory == null ? null : maritalHistory.trim();
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

    public String getHealthCheckup() {
        return healthCheckup;
    }

    public void setHealthCheckup(String healthCheckup) {
        this.healthCheckup = healthCheckup == null ? null : healthCheckup.trim();
    }

    public String getSpecialityCheckup() {
        return specialityCheckup;
    }

    public void setSpecialityCheckup(String specialityCheckup) {
        this.specialityCheckup = specialityCheckup == null ? null : specialityCheckup.trim();
    }

    public String getAuxiliaryExamination() {
        return auxiliaryExamination;
    }

    public void setAuxiliaryExamination(String auxiliaryExamination) {
        this.auxiliaryExamination = auxiliaryExamination == null ? null : auxiliaryExamination.trim();
    }

    public String getTentativeDiagnosis() {
        return tentativeDiagnosis;
    }

    public void setTentativeDiagnosis(String tentativeDiagnosis) {
        this.tentativeDiagnosis = tentativeDiagnosis == null ? null : tentativeDiagnosis.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public Date getDoctorSign() {
        return doctorSign;
    }

    public void setDoctorSign(Date doctorSign) {
        this.doctorSign = doctorSign;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }
}