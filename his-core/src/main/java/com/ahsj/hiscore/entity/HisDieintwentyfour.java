package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisDieintwentyfour extends BaseEntity {
    private Long id;

    private Long patientid;

    private String name;

    private Integer birthday;

    private String location;

    private Integer age;

    private Integer isMarried;

    private String job;

    private Integer sex;

    private String birthplace;

    private String employer;

    private String people;

    private String provide;

    private Long departmentid;

    private Long bedid;

    private String hospitalnumber;

    private String illarea;

    private Date inhospitaltime;

    private Date recordtime;

    private String complaint;

    private String insituation;

    private String indiagnosis;

    private String treatment;

    private Date dietime;

    private String diereason;

    private String diediagnosis;

    private Long doctorid;



    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientid() {
        return patientid;
    }

    public void setPatientid(Long patientid) {
        this.patientid = patientid;
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

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer == null ? null : employer.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getProvide() {
        return provide;
    }

    public void setProvide(String provide) {
        this.provide = provide == null ? null : provide.trim();
    }

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    public Long getBedid() {
        return bedid;
    }

    public void setBedid(Long bedid) {
        this.bedid = bedid;
    }

    public String getHospitalnumber() {
        return hospitalnumber;
    }

    public void setHospitalnumber(String hospitalnumber) {
        this.hospitalnumber = hospitalnumber == null ? null : hospitalnumber.trim();
    }

    public String getIllarea() {
        return illarea;
    }

    public void setIllarea(String illarea) {
        this.illarea = illarea == null ? null : illarea.trim();
    }

    public Date getInhospitaltime() {
        return inhospitaltime;
    }

    public void setInhospitaltime(Date inhospitaltime) {
        this.inhospitaltime = inhospitaltime;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint == null ? null : complaint.trim();
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

    public Date getDietime() {
        return dietime;
    }

    public void setDietime(Date dietime) {
        this.dietime = dietime;
    }

    public String getDiereason() {
        return diereason;
    }

    public void setDiereason(String diereason) {
        this.diereason = diereason == null ? null : diereason.trim();
    }

    public String getDiediagnosis() {
        return diediagnosis;
    }

    public void setDiediagnosis(String diediagnosis) {
        this.diediagnosis = diediagnosis == null ? null : diediagnosis.trim();
    }

    public Long getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Long doctorid) {
        this.doctorid = doctorid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}