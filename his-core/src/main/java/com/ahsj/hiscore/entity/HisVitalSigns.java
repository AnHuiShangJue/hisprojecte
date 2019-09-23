package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisVitalSigns extends BaseEntity{
    private Long id;

    private Long hospitalManageId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private Date time;

    private Double bodyTemperature;

    private Integer heartRate;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "consciousness_level", typeName = "consciousnessLevelName")
    private Integer consciousnessLevel;
    private String consciousnessLevelName;

    private String bloodPressure;

    private Integer breathe;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "degree_pain", typeName = "degreePainName")
    private Integer degreePain;
    private String degreePainName;

    private String earlyWarningScore;

    private Double urineVolume;

    private Integer stool;

    private String oxygenSaturation;

    private Double weight;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "drug_allergy", typeName = "drugAllergyName")
    private Integer drugAllergy;
    private String drugAllergyName;

    private Double drainage;

    private String hospitalManageNumber;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHospitalManageId() {
        return hospitalManageId;
    }

    public void setHospitalManageId(Long hospitalManageId) {
        this.hospitalManageId = hospitalManageId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getConsciousnessLevel() {
        return consciousnessLevel;
    }

    public void setConsciousnessLevel(Integer consciousnessLevel) {
        this.consciousnessLevel = consciousnessLevel;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
    }

    public Integer getBreathe() {
        return breathe;
    }

    public void setBreathe(Integer breathe) {
        this.breathe = breathe;
    }

    public String getEarlyWarningScore() {
        return earlyWarningScore;
    }

    public void setEarlyWarningScore(String earlyWarningScore) {
        this.earlyWarningScore = earlyWarningScore == null ? null : earlyWarningScore.trim();
    }

    public Double getUrineVolume() {
        return urineVolume;
    }

    public void setUrineVolume(Double urineVolume) {
        this.urineVolume = urineVolume;
    }

    public Integer getStool() {
        return stool;
    }

    public void setStool(Integer stool) {
        this.stool = stool;
    }

    public String getOxygenSaturation() {
        return oxygenSaturation;
    }

    public void setOxygenSaturation(String oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation == null ? null : oxygenSaturation.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getDrugAllergy() {
        return drugAllergy;
    }

    public void setDrugAllergy(Integer drugAllergy) {
        this.drugAllergy = drugAllergy;
    }

    public Double getDrainage() {
        return drainage;
    }

    public void setDrainage(Double drainage) {
        this.drainage = drainage;
    }

    public String getHospitalManageNumber() {
        return hospitalManageNumber;
    }

    public void setHospitalManageNumber(String hospitalManageNumber) {
        this.hospitalManageNumber = hospitalManageNumber == null ? null : hospitalManageNumber.trim();
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

    public String getDrugAllergyName() {
        return drugAllergyName;
    }

    public void setDrugAllergyName(String drugAllergyName) {
        this.drugAllergyName = drugAllergyName;
    }

    public String getConsciousnessLevelName() {
        return consciousnessLevelName;
    }

    public void setConsciousnessLevelName(String consciousnessLevelName) {
        this.consciousnessLevelName = consciousnessLevelName;
    }

    public String getDegreePainName() {
        return degreePainName;
    }

    public void setDegreePainName(String degreePainName) {
        this.degreePainName = degreePainName;
    }

    public Integer getDegreePain() {
        return degreePain;
    }

    public void setDegreePain(Integer degreePain) {
        this.degreePain = degreePain;
    }
}