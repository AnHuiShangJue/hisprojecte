package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisInoutTwentyfourHours extends BaseEntity {
    private Long id;

    private Long patientId;

    private String name;

    private Integer birthday;

    private String location;

    private Integer age;

    private Long phonenumber;

    private String idcard;

    private String remarks;

    private Integer height;

    private Double weight;

    private Integer isMarried;

    private String job;

    private Integer sex;

    private String birthplace;

    private String career;

    private String employer;

    private String medicalhistory;

    private String complaint;

    private String inhospitaltime;

    private String insituation;

    private String indiagnosis;

    private String treatment;

    private String outhospitaltime;

    private String outsituation;

    private String outdiagnosis;

    private String outinstructions;

    private String hospitalnumber;

    private Long doctorid;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
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

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer == null ? null : employer.trim();
    }

    public String getMedicalhistory() {
        return medicalhistory;
    }

    public void setMedicalhistory(String medicalhistory) {
        this.medicalhistory = medicalhistory == null ? null : medicalhistory.trim();
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint == null ? null : complaint.trim();
    }

    public String getInhospitaltime() {
        return inhospitaltime;
    }

    public void setInhospitaltime(String inhospitaltime) {
        this.inhospitaltime = inhospitaltime == null ? null : inhospitaltime.trim();
    }

    public String getInsituation() {
        return insituation;
    }

    public void setInsituation(String insituation) {
        this.insituation = insituation == null ? null : insituation.trim();
    }

    public String getIndiagnosis() {
        return indiagnosis;
    }

    public void setIndiagnosis(String indiagnosis) {
        this.indiagnosis = indiagnosis == null ? null : indiagnosis.trim();
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment == null ? null : treatment.trim();
    }

    public String getOuthospitaltime() {
        return outhospitaltime;
    }

    public void setOuthospitaltime(String outhospitaltime) {
        this.outhospitaltime = outhospitaltime == null ? null : outhospitaltime.trim();
    }

    public String getOutsituation() {
        return outsituation;
    }

    public void setOutsituation(String outsituation) {
        this.outsituation = outsituation == null ? null : outsituation.trim();
    }

    public String getOutdiagnosis() {
        return outdiagnosis;
    }

    public void setOutdiagnosis(String outdiagnosis) {
        this.outdiagnosis = outdiagnosis == null ? null : outdiagnosis.trim();
    }

    public String getOutinstructions() {
        return outinstructions;
    }

    public void setOutinstructions(String outinstructions) {
        this.outinstructions = outinstructions == null ? null : outinstructions.trim();
    }

    public Long getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Long doctorid) {
        this.doctorid = doctorid;
    }


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getHospitalnumber() {
        return hospitalnumber;
    }

    public void setHospitalnumber(String hospitalnumber) {
        this.hospitalnumber = hospitalnumber;
    }
}