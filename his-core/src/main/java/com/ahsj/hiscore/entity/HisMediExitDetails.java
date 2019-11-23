package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisMediExitDetails extends BaseEntity {
    private Long id;

    private Long pharmacyId;

    private Integer exitCount;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date validityPeriod;

    private String description;

    private String remarks;

    private Integer isPay;

    private Integer isBack;

    private Integer isDel;

    private Integer count;

    private BigDecimal totalPrice;

    private BigDecimal price;

    private String drugsNumb;

    private String drugsName;

    private String drugsAlias;

    private String drugsSpec;

    private String exitNumber;

    private String recordNumber;

    private String patientName;

    private String userName;

    private String  tdrugsName;

    private String  tdrugsSpec;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private String tollNumber;

    private Long mediEnterId;

    private String tdescription;

    private Integer sex;

    private String sexName;

    private Integer age;

    private String doctorName;

    private BigDecimal toll;

    public BigDecimal getToll() {
        return toll;
    }

    public void setToll(BigDecimal toll) {
        this.toll = toll;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTdescription() {
        return tdescription;
    }

    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }

    public String getTdrugsName() {
        return tdrugsName;
    }

    public void setTdrugsName(String tdrugsName) {
        this.tdrugsName = tdrugsName;
    }

    public String getTdrugsSpec() {
        return tdrugsSpec;
    }

    public void setTdrugsSpec(String tdrugsSpec) {
        this.tdrugsSpec = tdrugsSpec;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(String exitNumber) {
        this.exitNumber = exitNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Integer getExitCount() {
        return exitCount;
    }

    public void setExitCount(Integer exitCount) {
        this.exitCount = exitCount;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb == null ? null : drugsNumb.trim();
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName == null ? null : drugsName.trim();
    }

    public String getDrugsAlias() {
        return drugsAlias;
    }

    public void setDrugsAlias(String drugsAlias) {
        this.drugsAlias = drugsAlias == null ? null : drugsAlias.trim();
    }

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec == null ? null : drugsSpec.trim();
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getTollNumber() {
        return tollNumber;
    }

    public void setTollNumber(String tollNumber) {
        this.tollNumber = tollNumber;
    }

    public Long getMediEnterId() {
        return mediEnterId;
    }

    public void setMediEnterId(Long mediEnterId) {
        this.mediEnterId = mediEnterId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}