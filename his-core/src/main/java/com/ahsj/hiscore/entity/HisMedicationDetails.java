package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisMedicationDetails extends BaseEntity {
    private Long id;

    private Long mId; //药品基本表ID；

    private Long medicationId;

    private String description;

    private String remarks;

    private String medicalNumber;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPayName")
    private Integer isPay;

    private String isPayName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_back", typeName = "isBacks")
    private Integer isBack;

    private String isBacks;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_del", typeName = "isDels")
    private Integer isDel;

    private String isDels;

    private Integer count;

    private String drugsNumb;

    private String drugsName;

    private String tdrugsName;//翻译字段

    private String drugsAlias;

    private String drugsSpec;

    private String tdrugsSpec;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "prescription", typeName = "prescriptions")
    private Integer prescription;

    private String prescriptions;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "mental_medicine", typeName = "mentalMedicines")
    private Integer mentalMedicine;

    private String mentalMedicines;

    private String largeUnit;

    private String smallUnit;

    private Short conversionRate;
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "disable", typeName = "disables")

    private Integer disable;

    private String disables;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "level", typeName = "levels")
    private Integer level;

    private String levels;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "medical_insurance_sign", typeName = "medicalInsuranceSigns")
    private Integer medicalInsuranceSign;

    private String medicalInsuranceSigns;

    private String placeorigin;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "base_medicine", typeName = "baseMedicines")
    private Integer baseMedicine;

    private String baseMedicines;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "narcotic_drugs", typeName = "narcoticDrugss")
    private Integer narcoticDrugs;

    private String narcoticDrugss;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss ")
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

    private BigDecimal salePrice;

    private BigDecimal price;

    private BigDecimal medicineTotalPrice;//药品单项总价

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsBacks() {
        return isBacks;
    }

    public void setIsBacks(String isBacks) {
        this.isBacks = isBacks;
    }

    public String getIsDels() {
        return isDels;
    }

    public void setIsDels(String isDels) {
        this.isDels = isDels;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getMentalMedicines() {
        return mentalMedicines;
    }

    public void setMentalMedicines(String mentalMedicines) {
        this.mentalMedicines = mentalMedicines;
    }

    public String getDisables() {
        return disables;
    }

    public void setDisables(String disables) {
        this.disables = disables;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getMedicalInsuranceSigns() {
        return medicalInsuranceSigns;
    }

    public void setMedicalInsuranceSigns(String medicalInsuranceSigns) {
        this.medicalInsuranceSigns = medicalInsuranceSigns;
    }

    public String getBaseMedicines() {
        return baseMedicines;
    }

    public void setBaseMedicines(String baseMedicines) {
        this.baseMedicines = baseMedicines;
    }

    public String getNarcoticDrugss() {
        return narcoticDrugss;
    }

    public void setNarcoticDrugss(String narcoticDrugss) {
        this.narcoticDrugss = narcoticDrugss;
    }

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

    private String tollNumber;//交易流水号

    private Integer alreadyout;


    private String exitCreateUserName;//药品出库操作人姓名

    private String tollRecordNumber;//交易流水号
    public String getExitCreateUserName() {
        return exitCreateUserName;
    }

    public void setExitCreateUserName(String exitCreateUserName) {
        this.exitCreateUserName = exitCreateUserName;
    }

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

    public String getTdrugsSpec() {
        return tdrugsSpec;
    }

    public void setTdrugsSpec(String tdrugsSpec) {
        this.tdrugsSpec = tdrugsSpec;
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

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public String getTdrugsName() {
        return tdrugsName;
    }

    public void setTdrugsName(String tdrugsName) {
        this.tdrugsName = tdrugsName;
    }

    public String getTollNumber() {
        return tollNumber;
    }

    public void setTollNumber(String tollNumber) {
        this.tollNumber = tollNumber;
    }

    public String getTollRecordNumber() {
        return tollRecordNumber;
    }

    public void setTollRecordNumber(String tollRecordNumber) {
        this.tollRecordNumber = tollRecordNumber;
    }

    public BigDecimal getMedicineTotalPrice() {
        return medicineTotalPrice;
    }

    public void setMedicineTotalPrice(BigDecimal medicineTotalPrice) {
        this.medicineTotalPrice = medicineTotalPrice;
    }

    public Integer getAlreadyout() {
        return alreadyout;
    }

    public void setAlreadyout(Integer alreadyout) {
        this.alreadyout = alreadyout;
    }

    @Override
    public String toString() {
        return "HisMedicationDetails{" +
                "id=" + id +
                ", mId=" + mId +
                ", medicationId=" + medicationId +
                ", description='" + description + '\'' +
                ", remarks='" + remarks + '\'' +
                ", medicalNumber='" + medicalNumber + '\'' +
                ", isPay=" + isPay +
                ", isPayName='" + isPayName + '\'' +
                ", isBack=" + isBack +
                ", isBacks='" + isBacks + '\'' +
                ", isDel=" + isDel +
                ", isDels='" + isDels + '\'' +
                ", count=" + count +
                ", drugsNumb='" + drugsNumb + '\'' +
                ", drugsName='" + drugsName + '\'' +
                ", tdrugsName='" + tdrugsName + '\'' +
                ", drugsAlias='" + drugsAlias + '\'' +
                ", drugsSpec='" + drugsSpec + '\'' +
                ", tdrugsSpec='" + tdrugsSpec + '\'' +
                ", prescription=" + prescription +
                ", prescriptions='" + prescriptions + '\'' +
                ", mentalMedicine=" + mentalMedicine +
                ", mentalMedicines='" + mentalMedicines + '\'' +
                ", largeUnit='" + largeUnit + '\'' +
                ", smallUnit='" + smallUnit + '\'' +
                ", conversionRate=" + conversionRate +
                ", disable=" + disable +
                ", disables='" + disables + '\'' +
                ", level=" + level +
                ", levels='" + levels + '\'' +
                ", medicalInsuranceSign=" + medicalInsuranceSign +
                ", medicalInsuranceSigns='" + medicalInsuranceSigns + '\'' +
                ", placeorigin='" + placeorigin + '\'' +
                ", baseMedicine=" + baseMedicine +
                ", baseMedicines='" + baseMedicines + '\'' +
                ", narcoticDrugs=" + narcoticDrugs +
                ", narcoticDrugss='" + narcoticDrugss + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", totalPrice=" + totalPrice +
                ", medicalRecordId=" + medicalRecordId +
                ", counts=" + counts +
                ", highTime=" + highTime +
                ", lowTime=" + lowTime +
                ", salePrice=" + salePrice +
                ", price=" + price +
                ", medicineTotalPrice=" + medicineTotalPrice +
                ", isOut=" + isOut +
                ", isOutName='" + isOutName + '\'' +
                ", exitId=" + exitId +
                ", validityPeriod=" + validityPeriod +
                ", mediEnterId=" + mediEnterId +
                ", recordNumber='" + recordNumber + '\'' +
                ", actualCount=" + actualCount +
                ", surplus=" + surplus +
                ", tollNumber='" + tollNumber + '\'' +
                ", alreadyout=" + alreadyout +
                ", exitCreateUserName='" + exitCreateUserName + '\'' +
                ", tollRecordNumber='" + tollRecordNumber + '\'' +
                '}';
    }
}