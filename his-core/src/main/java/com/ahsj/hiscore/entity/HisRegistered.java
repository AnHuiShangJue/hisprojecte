package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisRegistered extends BaseEntity {
    private Long id;

    private String number;


    private int count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date highTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date lowTime;

    private Long serial;

    private String remark;

    private String idcard;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "registered_category", typeName = "categoryName")
    private Integer registeredCategory;
    String categoryName;

    private BigDecimal money;

    private BigDecimal amountReceivable;

    private BigDecimal paidAmount;

    private BigDecimal retrieveAmount;

    private Long patientId;

    private String patientName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "payName")
    private Integer isPay;
    String payName;

    private Long departmentId;

    private String departmentName;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_print", typeName = "printName")
    private Integer isPrint;
    String printName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_obsolete", typeName = "obsoleteName")
    private Integer isObsolete;
    String obsoleteName;
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_settlement", typeName = "settlementName")
    private Integer isSettlement;
    String settlementName;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date createDate;


    private  Integer isToday;


    private  Integer isReceived;


    public Integer getIsToday() {
        return isToday;
    }

    public void setIsToday(Integer isToday) {
        this.isToday = isToday;
    }

    private Long medicalRecordCardId;

    private Integer registerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getRegisteredCategory() {
        return registeredCategory;
    }

    public void setRegisteredCategory(Integer registeredCategory) {
        this.registeredCategory = registeredCategory;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }


    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }

    public Integer getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Integer isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Integer getIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(Integer isSettlement) {
        this.isSettlement = isSettlement;
    }


    public Long getMedicalRecordCardId() {
        return medicalRecordCardId;
    }

    public void setMedicalRecordCardId(Long medicalRecordCardId) {
        this.medicalRecordCardId = medicalRecordCardId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPrintName() {
        return printName;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public String getObsoleteName() {
        return obsoleteName;
    }

    public void setObsoleteName(String obsoleteName) {
        this.obsoleteName = obsoleteName;
    }

    public String getSettlementName() {
        return settlementName;
    }

    public void setSettlementName(String settlementName) {
        this.settlementName = settlementName;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public BigDecimal getAmountReceivable() {
        return amountReceivable;
    }

    public void setAmountReceivable(BigDecimal amountReceivable) {
        this.amountReceivable = amountReceivable;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getRetrieveAmount() {
        return retrieveAmount;
    }

    public void setRetrieveAmount(BigDecimal retrieveAmount) {
        this.retrieveAmount = retrieveAmount;
    }


    public Integer getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(Integer isReceived) {
        this.isReceived = isReceived;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "HisRegistered{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", serial='" + serial + '\'' +
                ", remark='" + remark + '\'' +
                ", idcard='" + idcard + '\'' +
                ", registeredCategory=" + registeredCategory +
                ", categoryName='" + categoryName + '\'' +
                ", money=" + money +
                ", amountReceivable=" + amountReceivable +
                ", paidAmount=" + paidAmount +
                ", retrieveAmount=" + retrieveAmount +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", isPay=" + isPay +
                ", payName='" + payName + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", isPrint=" + isPrint +
                ", printName='" + printName + '\'' +
                ", isObsolete=" + isObsolete +
                ", obsoleteName='" + obsoleteName + '\'' +
                ", isSettlement=" + isSettlement +
                ", settlementName='" + settlementName + '\'' +
                ", createDate=" + createDate +
                ", isToday=" + isToday +
                ", isReceived=" + isReceived +
                ", medicalRecordCardId=" + medicalRecordCardId +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getHighTime() {
        return highTime;
    }

    public void setHighTime(Date highTime) {
        this.highTime = highTime;
    }

    public Date getLowTime() {
        return lowTime;
    }

    public void setLowTime(Date lowTime) {
        this.lowTime = lowTime;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }
}