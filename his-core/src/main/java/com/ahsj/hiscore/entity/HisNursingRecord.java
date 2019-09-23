package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisNursingRecord extends BaseEntity{
    private Long id;

    private String manageNumber;

    private Double bodyTemperature;

    private String recordNumber;

    private String pulse;

    private String breathe;

    private String heartRate;

    private String bloodPressure;

    private String painScore;

    private String oxygenSaturation;

    private String flow;

    private String oxygenAbsorption;

    private String sucking;

    private String venousCatheterName;

    private String venousCatheterCare;

    private String involvementProject;

    private String involvement;

    private String outputProject;

    private String outputTraits;

    private String output;

    private String other;

    private String remark;

    private String signature;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dates;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOther;

    private Double weight;

    private String hospitalizationDay;

    public Date getDateOther() {
        return dateOther;
    }

    public void setDateOther(Date dateOther) {
        this.dateOther = dateOther;
    }

    public String getHospitalizationDay() {
        return hospitalizationDay;
    }

    public void setHospitalizationDay(String hospitalizationDay) {
        this.hospitalizationDay = hospitalizationDay;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManageNumber() {
        return manageNumber;
    }

    public void setManageNumber(String manageNumber) {
        this.manageNumber = manageNumber == null ? null : manageNumber.trim();
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber == null ? null : recordNumber.trim();
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse == null ? null : pulse.trim();
    }

    public String getBreathe() {
        return breathe;
    }

    public void setBreathe(String breathe) {
        this.breathe = breathe == null ? null : breathe.trim();
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate == null ? null : heartRate.trim();
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
    }

    public String getPainScore() {
        return painScore;
    }

    public void setPainScore(String painScore) {
        this.painScore = painScore == null ? null : painScore.trim();
    }

    public String getOxygenSaturation() {
        return oxygenSaturation;
    }

    public void setOxygenSaturation(String oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation == null ? null : oxygenSaturation.trim();
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow == null ? null : flow.trim();
    }

    public String getOxygenAbsorption() {
        return oxygenAbsorption;
    }

    public void setOxygenAbsorption(String oxygenAbsorption) {
        this.oxygenAbsorption = oxygenAbsorption == null ? null : oxygenAbsorption.trim();
    }

    public String getSucking() {
        return sucking;
    }

    public void setSucking(String sucking) {
        this.sucking = sucking == null ? null : sucking.trim();
    }

    public String getVenousCatheterName() {
        return venousCatheterName;
    }

    public void setVenousCatheterName(String venousCatheterName) {
        this.venousCatheterName = venousCatheterName == null ? null : venousCatheterName.trim();
    }

    public String getVenousCatheterCare() {
        return venousCatheterCare;
    }

    public void setVenousCatheterCare(String venousCatheterCare) {
        this.venousCatheterCare = venousCatheterCare == null ? null : venousCatheterCare.trim();
    }

    public String getInvolvementProject() {
        return involvementProject;
    }

    public void setInvolvementProject(String involvementProject) {
        this.involvementProject = involvementProject == null ? null : involvementProject.trim();
    }

    public String getInvolvement() {
        return involvement;
    }

    public void setInvolvement(String involvement) {
        this.involvement = involvement == null ? null : involvement.trim();
    }

    public String getOutputProject() {
        return outputProject;
    }

    public void setOutputProject(String outputProject) {
        this.outputProject = outputProject == null ? null : outputProject.trim();
    }

    public String getOutputTraits() {
        return outputTraits;
    }

    public void setOutputTraits(String outputTraits) {
        this.outputTraits = outputTraits == null ? null : outputTraits.trim();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output == null ? null : output.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

}