package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SurgicalRecord extends BaseEntity {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Long createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private Long updateUserId;

    private String patientName;

    private Integer age;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer sex;
    private String sexName;

    private Integer department;

    private String departmentName;

    private String bedNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String preoperativeDiagnosis;

    private String postoperativeDiagnosis;

    private String pathologicalDiagnosis;

    private String surgical;

    private String preanesthesiaMedication;

    private String medicationDuringSurgery;

    private String step;

    private String surgery;

    private String assistant;

    private String nurse;

    private String medicalNumber;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo == null ? null : bedNo.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPreoperativeDiagnosis() {
        return preoperativeDiagnosis;
    }

    public void setPreoperativeDiagnosis(String preoperativeDiagnosis) {
        this.preoperativeDiagnosis = preoperativeDiagnosis == null ? null : preoperativeDiagnosis.trim();
    }

    public String getPostoperativeDiagnosis() {
        return postoperativeDiagnosis;
    }

    public void setPostoperativeDiagnosis(String postoperativeDiagnosis) {
        this.postoperativeDiagnosis = postoperativeDiagnosis == null ? null : postoperativeDiagnosis.trim();
    }

    public String getPathologicalDiagnosis() {
        return pathologicalDiagnosis;
    }

    public void setPathologicalDiagnosis(String pathologicalDiagnosis) {
        this.pathologicalDiagnosis = pathologicalDiagnosis == null ? null : pathologicalDiagnosis.trim();
    }

    public String getSurgical() {
        return surgical;
    }

    public void setSurgical(String surgical) {
        this.surgical = surgical == null ? null : surgical.trim();
    }

    public String getPreanesthesiaMedication() {
        return preanesthesiaMedication;
    }

    public void setPreanesthesiaMedication(String preanesthesiaMedication) {
        this.preanesthesiaMedication = preanesthesiaMedication == null ? null : preanesthesiaMedication.trim();
    }

    public String getMedicationDuringSurgery() {
        return medicationDuringSurgery;
    }

    public void setMedicationDuringSurgery(String medicationDuringSurgery) {
        this.medicationDuringSurgery = medicationDuringSurgery == null ? null : medicationDuringSurgery.trim();
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step == null ? null : step.trim();
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery == null ? null : surgery.trim();
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant == null ? null : assistant.trim();
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse == null ? null : nurse.trim();
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber == null ? null : medicalNumber.trim();
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
}