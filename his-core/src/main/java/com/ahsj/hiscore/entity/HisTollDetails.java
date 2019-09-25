package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisTollDetails extends BaseEntity {
    private Long id;

    private Long tollRecordId;

    private String name;

    private String records;

    private Long targetId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "type", typeName = "recordTypes")
    private Integer type;
    private String recordTypes;

    private BigDecimal money;

    private BigDecimal price;

    private BigDecimal recoverTheFee;

    private String description;

    private String MedicalReocordNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private String createName;
    private BigDecimal totalPrices;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "payName")
    private Integer isPay;
    private String payName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_settlement", typeName = "isSettlements")
    private Integer isSettlement;
    private String isSettlements;

    private String tranName;//翻译名称

    private String commonNumber;//公共编号 可表示挂号编号，就诊编号，住院编号 服务于通用刷卡模块

    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }

    public Integer getIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(Integer isSettlement) {
        this.isSettlement = isSettlement;
    }

    public String getIsSettlements() {
        return isSettlements;
    }

    public void setIsSettlements(String isSettlements) {
        this.isSettlements = isSettlements;
    }

    private String MedicalRecordId;

    private Integer num;

    private String registerNumber;//挂号编号

    private String tdrugsSpec;

    private String drugsSpec;

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec;
    }

    public String getTdrugsSpec() {
        return tdrugsSpec;
    }

    public void setTdrugsSpec(String tdrugsSpec) {
        this.tdrugsSpec = tdrugsSpec;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMedicalRecordId() {
        return MedicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        MedicalRecordId = medicalRecordId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTollRecordId() {
        return tollRecordId;
    }

    public void setTollRecordId(Long tollRecordId) {
        this.tollRecordId = tollRecordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRecoverTheFee() {
        return recoverTheFee;
    }

    public void setRecoverTheFee(BigDecimal recoverTheFee) {
        this.recoverTheFee = recoverTheFee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedicalReocordNumber() {
        return MedicalReocordNumber;
    }

    public void setMedicalReocordNumber(String medicalReocordNumber) {
        MedicalReocordNumber = medicalReocordNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(String recordTypes) {
        this.recordTypes = recordTypes;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public BigDecimal getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getCommonNumber() {
        return commonNumber;
    }

    public void setCommonNumber(String commonNumber) {
        this.commonNumber = commonNumber;
    }
}