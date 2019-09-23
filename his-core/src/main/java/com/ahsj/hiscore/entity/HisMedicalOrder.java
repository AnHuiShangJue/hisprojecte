package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisMedicalOrder extends BaseEntity {
    private Long id;

    private Long hosptalregistId;

    private Long patientId;

    private String recordId;

    private String hosptalregistNumber;

    private String number;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "medical_order_type", typeName = "typeName")
    private Integer type;
    private String typeName;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    private Integer orderNum;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_proofreading", typeName = "isProofreadingName")
    private Integer isProofreading;
    private String isProofreadingName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_approved", typeName = "isApprovedName")
    private Integer isApproved;
    private String isApprovedName;
    //以下字段是查询出来为显示要求所声明的，在数据库本表中并非实际存在
    private String medicalOrderName;//医嘱名称
    private Integer isSkinTest;//是否皮试
    private String specification;//规格
    private String unit;//单位
    private String usages;//用法
    private String dosage;//用量
    private String remarks;//医嘱备注
    private Integer intervals;//医嘱间隔
    private String pnNumber;//校对护士编号
    private String pnName;//校对护士姓名
    private String anNumber;//核准护士编号
    private String anName;//核准护士姓名
    private Integer isStop;//是否停嘱
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date stopDate;//停嘱时间
    private String userName;//停嘱人姓名(也可用作其他与user_info表中的用户名)
    private Integer userType;//停嘱人身份类型
    private String spnNumber;//停嘱校对护士编号
    private String spnName;//停嘱校对护士姓名
    private String sanNumber;//核准校对护士编号
    private String sanName;//核准校对护士姓名
    private BigDecimal totalAmount;//总量


    public Integer getIsProofreading() {
        return isProofreading;
    }

    public void setIsProofreading(Integer isProofreading) {
        this.isProofreading = isProofreading;
    }

    public String getIsProofreadingName() {
        return isProofreadingName;
    }

    public void setIsProofreadingName(String isProofreadingName) {
        this.isProofreadingName = isProofreadingName;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public String getIsApprovedName() {
        return isApprovedName;
    }

    public void setIsApprovedName(String isApprovedName) {
        this.isApprovedName = isApprovedName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHosptalregistId() {
        return hosptalregistId;
    }

    public void setHosptalregistId(Long hosptalregistId) {
        this.hosptalregistId = hosptalregistId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getHosptalregistNumber() {
        return hosptalregistNumber;
    }

    public void setHosptalregistNumber(String hosptalregistNumber) {
        this.hosptalregistNumber = hosptalregistNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getMedicalOrderName() {
        return medicalOrderName;
    }

    public void setMedicalOrderName(String medicalOrderName) {
        this.medicalOrderName = medicalOrderName;
    }

    public Integer getIsSkinTest() {
        return isSkinTest;
    }

    public void setIsSkinTest(Integer isSkinTest) {
        this.isSkinTest = isSkinTest;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIntervals() {
        return intervals;
    }

    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }

    public String getPnNumber() {
        return pnNumber;
    }

    public void setPnNumber(String pnNumber) {
        this.pnNumber = pnNumber;
    }

    public String getPnName() {
        return pnName;
    }

    public void setPnName(String pnName) {
        this.pnName = pnName;
    }

    public String getAnNumber() {
        return anNumber;
    }

    public void setAnNumber(String anNumber) {
        this.anNumber = anNumber;
    }

    public String getAnName() {
        return anName;
    }

    public void setAnName(String anName) {
        this.anName = anName;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSpnNumber() {
        return spnNumber;
    }

    public void setSpnNumber(String spnNumber) {
        this.spnNumber = spnNumber;
    }

    public String getSpnName() {
        return spnName;
    }

    public void setSpnName(String spnName) {
        this.spnName = spnName;
    }

    public String getSanNumber() {
        return sanNumber;
    }

    public void setSanNumber(String sanNumber) {
        this.sanNumber = sanNumber;
    }

    public String getSanName() {
        return sanName;
    }

    public void setSanName(String sanName) {
        this.sanName = sanName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}