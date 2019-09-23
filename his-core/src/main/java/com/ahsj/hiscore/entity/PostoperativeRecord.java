package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PostoperativeRecord  extends BaseEntity {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Long createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private Long updateUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinDate;

    private String patientName;

    private String briefCondition;

    private String preoperativeDiagnosis;

    private String surgicalIndication;

    private String prposedSurgeryNamemethod;

    private String smulatedAnesthesia;

    private String precautions;

    private String operationName;

    private String operationAfter;

    private String surgicalConsiderations;

    private String medicalNumber;

    private String operationTime;

    private String patientCondition;

    private Integer age;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer sex;
    private String sexName;

    private Integer department;

    private String departmentName;

    private String bedNo;

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getBriefCondition() {
        return briefCondition;
    }

    public void setBriefCondition(String briefCondition) {
        this.briefCondition = briefCondition == null ? null : briefCondition.trim();
    }

    public String getPreoperativeDiagnosis() {
        return preoperativeDiagnosis;
    }

    public void setPreoperativeDiagnosis(String preoperativeDiagnosis) {
        this.preoperativeDiagnosis = preoperativeDiagnosis == null ? null : preoperativeDiagnosis.trim();
    }

    public String getSurgicalIndication() {
        return surgicalIndication;
    }

    public void setSurgicalIndication(String surgicalIndication) {
        this.surgicalIndication = surgicalIndication == null ? null : surgicalIndication.trim();
    }

    public String getPrposedSurgeryNamemethod() {
        return prposedSurgeryNamemethod;
    }

    public void setPrposedSurgeryNamemethod(String prposedSurgeryNamemethod) {
        this.prposedSurgeryNamemethod = prposedSurgeryNamemethod == null ? null : prposedSurgeryNamemethod.trim();
    }

    public String getSmulatedAnesthesia() {
        return smulatedAnesthesia;
    }

    public void setSmulatedAnesthesia(String smulatedAnesthesia) {
        this.smulatedAnesthesia = smulatedAnesthesia == null ? null : smulatedAnesthesia.trim();
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions == null ? null : precautions.trim();
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public String getOperationAfter() {
        return operationAfter;
    }

    public void setOperationAfter(String operationAfter) {
        this.operationAfter = operationAfter == null ? null : operationAfter.trim();
    }

    public String getSurgicalConsiderations() {
        return surgicalConsiderations;
    }

    public void setSurgicalConsiderations(String surgicalConsiderations) {
        this.surgicalConsiderations = surgicalConsiderations == null ? null : surgicalConsiderations.trim();
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber == null ? null : medicalNumber.trim();
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime == null ? null : operationTime.trim();
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition == null ? null : patientCondition.trim();
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
}