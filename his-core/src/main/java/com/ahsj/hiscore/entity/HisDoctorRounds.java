package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisDoctorRounds extends BaseEntity {
    private Long id;

    private Long rounddoctor;

    private Long recorddoctor;

    private Date roundtime;

    private String technicaljob;

    private String condition;

    private String diagnosis;

    private String differentialdiagnosis;

    private String opinion;

    private String analysis;

    private String sign;

    private String illhistory;

    private String doctorsign;

    private String remark;

    private String hoptipalrecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRounddoctor() {
        return rounddoctor;
    }

    public void setRounddoctor(Long rounddoctor) {
        this.rounddoctor = rounddoctor;
    }

    public Long getRecorddoctor() {
        return recorddoctor;
    }

    public void setRecorddoctor(Long recorddoctor) {
        this.recorddoctor = recorddoctor;
    }

    public Date getRoundtime() {
        return roundtime;
    }

    public void setRoundtime(Date roundtime) {
        this.roundtime = roundtime;
    }

    public String getTechnicaljob() {
        return technicaljob;
    }

    public void setTechnicaljob(String technicaljob) {
        this.technicaljob = technicaljob == null ? null : technicaljob.trim();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    public String getDifferentialdiagnosis() {
        return differentialdiagnosis;
    }

    public void setDifferentialdiagnosis(String differentialdiagnosis) {
        this.differentialdiagnosis = differentialdiagnosis == null ? null : differentialdiagnosis.trim();
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getIllhistory() {
        return illhistory;
    }

    public void setIllhistory(String illhistory) {
        this.illhistory = illhistory == null ? null : illhistory.trim();
    }

    public String getDoctorsign() {
        return doctorsign;
    }

    public void setDoctorsign(String doctorsign) {
        this.doctorsign = doctorsign == null ? null : doctorsign.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getHoptipalrecord() {
        return hoptipalrecord;
    }

    public void setHoptipalrecord(String hoptipalrecord) {
        this.hoptipalrecord = hoptipalrecord == null ? null : hoptipalrecord.trim();
    }
}