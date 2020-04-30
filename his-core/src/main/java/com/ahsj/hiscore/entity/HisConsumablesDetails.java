package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisConsumablesDetails extends BaseEntity {
    private Long id;

    private String name;

    private String consumablesCode;

    private String consumablesDetailsCode;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "consumable_type", typeName = "typeName")
    private Integer type;
    private String typeName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_outbound", typeName = "outboundName")
    private  Integer isOutbound;
    private String outboundName;

    private Long hospitalConsumablesDetailsId;


    private String spec;

    private Integer stock;
    private Integer stockWarn;

    private Double price;

    private Integer isDelete;

    //是否警告
    private Integer isWarning;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date validityPeriod;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDate;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date consumablesDetailsCreateDates;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applicationTime;





    private Double lowPrice;
    private Double highPrice;

    private Integer comsumablesNum;

    private String userNames;

    private String medicalRecordNumber;

    private String consumableNumber;

    public String getConsumablesDetailsCode() {
        return consumablesDetailsCode;
    }

    public void setConsumablesDetailsCode(String consumablesDetailsCode) {
        this.consumablesDetailsCode = consumablesDetailsCode;
    }

    public String getConsumablesCode() {
        return consumablesCode;
    }

    public void setConsumablesCode(String consumablesCode) {
        this.consumablesCode = consumablesCode;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getConsumablesDetailsCreateDates() {
        return consumablesDetailsCreateDates;
    }

    public void setConsumablesDetailsCreateDates(Date consumablesDetailsCreateDates) {
        this.consumablesDetailsCreateDates = consumablesDetailsCreateDates;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getConsumableNumber() {
        return consumableNumber;
    }

    public void setConsumableNumber(String consumableNumber) {
        this.consumableNumber = consumableNumber;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public Long getHospitalConsumablesDetailsId() {
        return hospitalConsumablesDetailsId;
    }

    public void setHospitalConsumablesDetailsId(Long hospitalConsumablesDetailsId) {
        this.hospitalConsumablesDetailsId = hospitalConsumablesDetailsId;
    }

    public Date getConsumablesDetailsCreateDate() {
        return consumablesDetailsCreateDate;
    }

    public void setConsumablesDetailsCreateDate(Date consumablesDetailsCreateDate) {
        this.consumablesDetailsCreateDate = consumablesDetailsCreateDate;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public Integer getComsumablesNum() {
        return comsumablesNum;
    }

    public void setComsumablesNum(Integer comsumablesNum) {
        this.comsumablesNum = comsumablesNum;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getStockWarn() {
        return stockWarn;
    }

    public void setStockWarn(Integer stockWarn) {
        this.stockWarn = stockWarn;
    }

    public Integer getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Integer isWarning) {
        this.isWarning = isWarning;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getIsOutbound() {
        return isOutbound;
    }

    public void setIsOutbound(Integer isOutbound) {
        this.isOutbound = isOutbound;
    }

    public String getOutboundName() {
        return outboundName;
    }

    public void setOutboundName(String outboundName) {
        this.outboundName = outboundName;
    }

    @Override
    public String toString() {
        return "HisConsumablesDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", isOutbound=" + isOutbound +
                ", outboundName='" + outboundName + '\'' +
                ", hospitalConsumablesDetailsId=" + hospitalConsumablesDetailsId +
                ", spec='" + spec + '\'' +
                ", stock=" + stock +
                ", stockWarn=" + stockWarn +
                ", price=" + price +
                ", isWarning=" + isWarning +
                ", validityPeriod=" + validityPeriod +
                ", consumablesDetailsCreateDate=" + consumablesDetailsCreateDate +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", comsumablesNum=" + comsumablesNum +
                ", userNames='" + userNames + '\'' +
                ", medicalRecordNumber='" + medicalRecordNumber + '\'' +
                ", consumableNumber='" + consumableNumber + '\'' +
                '}';
    }
}