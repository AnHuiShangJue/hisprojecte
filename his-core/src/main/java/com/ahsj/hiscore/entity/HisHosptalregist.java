package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisHosptalregist extends BaseEntity {
    private Long id;

    private Long patientId;

    private String number;

    private Long doctorId;

    private String medicalNumber;

    private Long nurseId;

    private Long wardId;

    private Long bedId;

    private Integer isFail;

    private Long departmentId;

    private String departmentName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "fee_type", typeName = "feeTypes")
    private Integer feeType; //费用类型（0：自费 1：医保
    private String feeTypes;
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "care_level", typeName = "careLevels")
    private Integer careLevel;
    private String careLevels;

    private String outpatientDiagnosis;

    private String inpatientDiagnosis;

    private Date jointime;

    private String remark;
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPays")
    private Integer isPay;
    private String isPays;

    //以下是字段属于病人表字段
    private String name;
    private String location;

    private Integer age;

    private Long phonenumber;

    private String idcard;
    private Integer height;

    private Double weight;
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer sex;
    String sexName;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_married", typeName = "married")
    private Integer isMarried;
    String married;

    public String getIsPays() {
        return isPays;
    }

    public void setIsPays(String isPays) {
        this.isPays = isPays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public Integer getIsFail() {
        return isFail;
    }

    public void setIsFail(Integer isFail) {
        this.isFail = isFail;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public String getFeeTypes() {
        return feeTypes;
    }

    public void setFeeTypes(String feeTypes) {
        this.feeTypes = feeTypes;
    }

    public Integer getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(Integer careLevel) {
        this.careLevel = careLevel;
    }

    public String getCareLevels() {
        return careLevels;
    }

    public void setCareLevels(String careLevels) {
        this.careLevels = careLevels;
    }

    public String getOutpatientDiagnosis() {
        return outpatientDiagnosis;
    }

    public void setOutpatientDiagnosis(String outpatientDiagnosis) {
        this.outpatientDiagnosis = outpatientDiagnosis;
    }

    public String getInpatientDiagnosis() {
        return inpatientDiagnosis;
    }

    public void setInpatientDiagnosis(String inpatientDiagnosis) {
        this.inpatientDiagnosis = inpatientDiagnosis;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        this.idcard = idcard;
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

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}