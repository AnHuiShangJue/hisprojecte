package com.ahsj.translate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisMedicationDetails extends BaseEntity {
    private Long id;

    private Long medicationId;

    private String description;

    private String remarks;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPayName")
    private Integer isPay;
    private String isPayName;

    private Integer isBack;

    private Integer isDel;

    private Integer count;

    private String drugsNumb;

    private String drugsName;

    private String drugsAlias;

    private String drugsSpec;

    private Integer prescription;

    private Integer mentalMedicine;

    private String largeUnit;

    private String smallUnit;

    private Short conversionRate;

    private Integer disable;

    private Integer level;

    private Integer medicalInsuranceSign;

    private String placeorigin;

    private Integer baseMedicine;

    private Integer narcoticDrugs;

    private Date createDate;

    private Date updateDate;

    private BigDecimal totalPrice;

    private Long medicalRecordId;

    private int counts;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date highTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date lowTime;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_out", typeName = "isOutName")
    private Integer isOut;
    private String isOutName;

    private Long exitId;

    //为多表查询所起的别名字段
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date validityPeriod;
    private Long mediEnterId;//对应药品入库ID
    private String recordNumber;//对应就诊记录编号
    private Integer actualCount;//根据就诊编号出药时某药品某有效期实际出货数量，足够时全从有效期最早的出，不足时从其他有效期出
    private Long surplus;//入库药品余量

    public Long getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(Long medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
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

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public Integer getMentalMedicine() {
        return mentalMedicine;
    }

    public void setMentalMedicine(Integer mentalMedicine) {
        this.mentalMedicine = mentalMedicine;
    }

    public String getLargeUnit() {
        return largeUnit;
    }

    public void setLargeUnit(String largeUnit) {
        this.largeUnit = largeUnit == null ? null : largeUnit.trim();
    }

    public String getSmallUnit() {
        return smallUnit;
    }

    public void setSmallUnit(String smallUnit) {
        this.smallUnit = smallUnit == null ? null : smallUnit.trim();
    }

    public Short getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Short conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMedicalInsuranceSign() {
        return medicalInsuranceSign;
    }

    public void setMedicalInsuranceSign(Integer medicalInsuranceSign) {
        this.medicalInsuranceSign = medicalInsuranceSign;
    }

    public String getPlaceorigin() {
        return placeorigin;
    }

    public void setPlaceorigin(String placeorigin) {
        this.placeorigin = placeorigin == null ? null : placeorigin.trim();
    }

    public Integer getBaseMedicine() {
        return baseMedicine;
    }

    public void setBaseMedicine(Integer baseMedicine) {
        this.baseMedicine = baseMedicine;
    }

    public Integer getNarcoticDrugs() {
        return narcoticDrugs;
    }

    public void setNarcoticDrugs(Integer narcoticDrugs) {
        this.narcoticDrugs = narcoticDrugs;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getIsPayName() {
        return isPayName;
    }

    public void setIsPayName(String isPayName) {
        this.isPayName = isPayName;
    }

    public Long getMediEnterId() {
        return mediEnterId;
    }

    public void setMediEnterId(Long mediEnterId) {
        this.mediEnterId = mediEnterId;
    }

    public Integer getIsOut() {
        return isOut;
    }

    public void setIsOut(Integer isOut) {
        this.isOut = isOut;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getIsOutName() {
        return isOutName;
    }

    public void setIsOutName(String isOutName) {
        this.isOutName = isOutName;
    }

    public Long getExitId() {
        return exitId;
    }

    public void setExitId(Long exitId) {
        this.exitId = exitId;
    }

    public Integer getActualCount() {
        return actualCount;
    }

    public void setActualCount(Integer actualCount) {
        this.actualCount = actualCount;
    }

    public Long getSurplus() {
        return surplus;
    }

    public void setSurplus(Long surplus) {
        this.surplus = surplus;
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

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }


    @Override
    public String toString() {
        return "HisMedicationDetails{" +
                "id=" + id +
                ", medicationId=" + medicationId +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isPay=" + isPay +
                ", isPayName='" + isPayName + '\'' +
                ", isBack=" + isBack +
                ", isDel=" + isDel +
                ", count=" + count +
                ", drugsNumb='" + drugsNumb + '\'' +
                ", drugsName='" + drugsName + '\'' +
                ", drugsAlias='" + drugsAlias + '\'' +
                ", drugsSpec='" + drugsSpec + '\'' +
                ", prescription=" + prescription +
                ", mentalMedicine=" + mentalMedicine +
                ", largeUnit='" + largeUnit + '\'' +
                ", smallUnit='" + smallUnit + '\'' +
                ", conversionRate=" + conversionRate +
                ", disable=" + disable +
                ", level=" + level +
                ", medicalInsuranceSign=" + medicalInsuranceSign +
                ", placeorigin='" + placeorigin + '\'' +
                ", baseMedicine=" + baseMedicine +
                ", narcoticDrugs=" + narcoticDrugs +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", totalPrice=" + totalPrice +
                ", medicalRecordId=" + medicalRecordId +
                ", counts=" + counts +
                ", highTime=" + highTime +
                ", lowTime=" + lowTime +
                ", isOut=" + isOut +
                ", isOutName='" + isOutName + '\'' +
                ", exitId=" + exitId +
                ", validityPeriod=" + validityPeriod +
                ", mediEnterId=" + mediEnterId +
                ", recordNumber='" + recordNumber + '\'' +
                ", actualCount=" + actualCount +
                ", surplus=" + surplus +
                '}';
    }
}