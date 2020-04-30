package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisExitConsumablesDetails extends BaseEntity {
    private Long id;

    private String exitNumber;

    private String hisHospitalManagerCode;

    private String consumablesCode;

    private String name;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "consumable_type", typeName = "typeName")
    private Integer type;
    private String typeName;

    private String spec;

    private Double price;

    private Double lowPrice;

    private Double highPrice;

    private Integer stock;

    private Integer exitCount;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date validityPeriod;

    private Integer isBack;

    private Integer isDelete;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    private Date lowexitDate;

    private Date highexitDate;

    private String personInCharge;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date expectedTime;

    private Date lowexpectedTime;

    private Date highexpectedTime;

    private String patientName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date lowTime;//下区间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date upTime;//上去间

    private String months;

    private String years;

    private String types;

    //翻译字段
    private String tName;

    private String tSpec;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Date getLowTime() {
        return lowTime;
    }

    public void setLowTime(Date lowTime) {
        this.lowTime = lowTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Date getLowexitDate() {
        return lowexitDate;
    }

    public void setLowexitDate(Date lowexitDate) {
        this.lowexitDate = lowexitDate;
    }

    public Date getHighexitDate() {
        return highexitDate;
    }

    public void setHighexitDate(Date highexitDate) {
        this.highexitDate = highexitDate;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Date getLowexpectedTime() {
        return lowexpectedTime;
    }

    public void setLowexpectedTime(Date lowexpectedTime) {
        this.lowexpectedTime = lowexpectedTime;
    }

    public Date getHighexpectedTime() {
        return highexpectedTime;
    }

    public void setHighexpectedTime(Date highexpectedTime) {
        this.highexpectedTime = highexpectedTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExitNumber() {
        return exitNumber;
    }

    public void setExitNumber(String exitNumber) {
        this.exitNumber = exitNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettSpec() {
        return tSpec;
    }

    public void settSpec(String tSpec) {
        this.tSpec = tSpec;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getHisHospitalManagerCode() {
        return hisHospitalManagerCode;
    }

    public void setHisHospitalManagerCode(String hisHospitalManagerCode) {
        this.hisHospitalManagerCode = hisHospitalManagerCode;
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
}