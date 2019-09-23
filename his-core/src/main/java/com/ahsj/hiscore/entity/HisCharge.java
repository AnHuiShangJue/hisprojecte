package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/13
 * @Time 17:38
 **/
public class HisCharge extends BaseEntity {
    /**
     * className hisCharge
     *
     * @author dingli
     * @date 2019/7/13 17:38
     */
    private String medicalRecordId;//就诊编号

    private String patientName;  //病人

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexName")
    private Integer sex;
    private String sexName;

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "record_type", typeName = "recordTypes")
    private Integer recordType;
    private String recordTypes;

    public String getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(String recordTypes) {
        this.recordTypes = recordTypes;
    }

    private String doctorName;

    private String recordName;

    private String number;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdName")
    private Long departmentId;
    private String departmentIdName;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    private Integer count;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPays")
    private Integer isPay;
    private String isPays;

    private Long id;

    private BigDecimal price;

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private BigDecimal amountPrice;

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(BigDecimal amountPrice) {
        this.amountPrice = amountPrice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
