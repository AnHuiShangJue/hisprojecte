package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisMedical extends BaseEntity {
    private Long id;

    private String patientIdcard;

    private String patientName;

    private Integer patientSex;

    private Integer patientAge;

    private String nowCondition;

    private String tnowCondition;

    private String tnowCondition1;

    private String hiscondition;

    private String thiscondition;

    private String thiscondition1;

    private String chiefcomplaint;

    private String tchiefcomplaint;

    private String tchiefcomplaint1;

    private String number;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    private String remarks;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    private String noSetDate;

    private String bloodPressure;

    private Long recordId;

    private String personalHistory;

    private String tpersonalHistory;

    private String tpersonalHistory1;

    private String allergies;

    private String tallergies;

    private String tallergies1;

    private String familyHistory;

    private String tfamilyHistory;

    private String tfamilyHistory1;

    private Double bodyTemperature;

    private Double bodyWeight;

    private Integer heartRate;

    private String other;

    private String tother;

    private String tother1;

    private String templateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard == null ? null : patientIdcard.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getTother() {
        return tother;
    }

    public void setTother(String tother) {
        this.tother = tother;
    }

    public Integer getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Integer patientSex) {
        this.patientSex = patientSex;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getNowCondition() {
        return nowCondition;
    }

    public void setNowCondition(String nowCondition) {
        this.nowCondition = nowCondition == null ? null : nowCondition.trim();
    }

    public String getHiscondition() {
        return hiscondition;
    }

    public void setHiscondition(String hiscondition) {
        this.hiscondition = hiscondition == null ? null : hiscondition.trim();
    }

    public String getChiefcomplaint() {
        return chiefcomplaint;
    }

    public void setChiefcomplaint(String chiefcomplaint) {
        this.chiefcomplaint = chiefcomplaint == null ? null : chiefcomplaint.trim();
    }

    public String getTnowCondition1() {
        return tnowCondition1;
    }

    public void setTnowCondition1(String tnowCondition1) {
        this.tnowCondition1 = tnowCondition1;
    }

    public String getThiscondition1() {
        return thiscondition1;
    }

    public void setThiscondition1(String thiscondition1) {
        this.thiscondition1 = thiscondition1;
    }

    public String getTchiefcomplaint1() {
        return tchiefcomplaint1;
    }

    public void setTchiefcomplaint1(String tchiefcomplaint1) {
        this.tchiefcomplaint1 = tchiefcomplaint1;
    }

    public String getTpersonalHistory1() {
        return tpersonalHistory1;
    }

    public void setTpersonalHistory1(String tpersonalHistory1) {
        this.tpersonalHistory1 = tpersonalHistory1;
    }

    public String getTallergies1() {
        return tallergies1;
    }

    public void setTallergies1(String tallergies1) {
        this.tallergies1 = tallergies1;
    }

    public String getTfamilyHistory1() {
        return tfamilyHistory1;
    }

    public void setTfamilyHistory1(String tfamilyHistory1) {
        this.tfamilyHistory1 = tfamilyHistory1;
    }

    public String getTother1() {
        return tother1;
    }

    public void setTother1(String tother1) {
        this.tother1 = tother1;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getNoSetDate() {
        return noSetDate;
    }

    public void setNoSetDate(String noSetDate) {
        this.noSetDate = noSetDate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory == null ? null : personalHistory.trim();
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies == null ? null : allergies.trim();
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public Double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getTnowCondition() {
        return tnowCondition;
    }

    public void setTnowCondition(String tnowCondition) {
        this.tnowCondition = tnowCondition;
    }

    public String getThiscondition() {
        return thiscondition;
    }

    public void setThiscondition(String thiscondition) {
        this.thiscondition = thiscondition;
    }

    public String getTchiefcomplaint() {
        return tchiefcomplaint;
    }

    public void setTchiefcomplaint(String tchiefcomplaint) {
        this.tchiefcomplaint = tchiefcomplaint;
    }

    public String getTpersonalHistory() {
        return tpersonalHistory;
    }

    public void setTpersonalHistory(String tpersonalHistory) {
        this.tpersonalHistory = tpersonalHistory;
    }

    public String getTallergies() {
        return tallergies;
    }

    public void setTallergies(String tallergies) {
        this.tallergies = tallergies;
    }

    public String getTfamilyHistory() {
        return tfamilyHistory;
    }

    public void setTfamilyHistory(String tfamilyHistory) {
        this.tfamilyHistory = tfamilyHistory;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}