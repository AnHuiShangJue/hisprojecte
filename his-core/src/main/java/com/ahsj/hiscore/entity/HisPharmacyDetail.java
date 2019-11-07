package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisPharmacyDetail extends BaseEntity {
    @ExcelColumn( value = "序号", col = 1)
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    private Long medicineId;

    private Integer count;

    @ExcelColumn( value = "进价", col = 6)
    private BigDecimal enterPrice;

    @ExcelColumn( value = "售价", col = 7)
    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false)
    private BigDecimal salePrice;
    private BigDecimal lowPrice;
    private BigDecimal highPrice;

    @ExcelColumn( value = "导入药品数量", col = 8)
    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = true, nullable = false)
    private Integer stock;

    @ExcelColumn( value = "已售数目", col = 11)
    @ColumnCheckAnnotation(index = 9, length = 64, isRepeat = true, nullable = false)
    private Integer saleCounts;
    private Integer lowCount;
    private Integer highCount;


    @ColumnCheckAnnotation(index =16, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "is_obtained")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_obtained",typeName = "obtained")
    private Integer isObtained;
    @ExcelColumn( value = "是否下架", col = 12)
    private String obtained;

    private String description;

    @ExcelColumn( value = "药品编号", col = 2)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private String drugsNumb;

    @ExcelColumn( value = "药品名称", col = 3)
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false)
    private String drugsName;

    private String drugsAlias;

    @ExcelColumn( value = "药品规格", col = 4)
    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = true, nullable = false)
    private String drugsSpec;

    @ColumnCheckAnnotation(index = 10, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "prescription")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "prescription",typeName = "precriptionName")
    private Integer prescription;
    private String precriptionName;

    @ColumnCheckAnnotation(index = 11, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "mental_medicine")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "mental_medicine",typeName = "mentalmedicine")
    private Integer mentalMedicine;
    private String mentalmedicine;

    private String largeUnit;

    private String smallUnit;

    private Short conversionRate;


    @ColumnCheckAnnotation(index =15, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "disable")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "disable",typeName = "disabled")
    private Integer disable;
    @ExcelColumn( value = "是否停用", col = 13)
    private String disabled;

    @ColumnCheckAnnotation(index = 12, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "level")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "level",typeName = "leveld")
    private Integer level;
    private String leveld;

    @ColumnCheckAnnotation(index = 13, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "medical_insurance_sign")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "medical_insurance_sign",typeName = "medicalinsurancesign")
    private Integer medicalInsuranceSign;
    private String medicalinsurancesign;

    @ColumnCheckAnnotation(index =14, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "placeOrigin")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "placeOrigin",typeName = "placeoriginName")
    private Integer placeorigin;
    private String placeoriginName;

    @ColumnCheckAnnotation(index = 17, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "base_medicine")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "base_medicine",typeName = "basemedicine")
    private Integer baseMedicine;
    private String basemedicine;

    @ColumnCheckAnnotation(index = 18, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "narcotic_drugs")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "narcotic_drugs",typeName = "narcoticdrugs")
    private Integer narcoticDrugs;
    private String narcoticdrugs;

    @ColumnCheckAnnotation(index = 19, length = 64, isRepeat = true, nullable = false)
    private String remarks;

    @ExcelColumn( value = "药品有效期", importFormat ="yyyy/MM/dd" ,col = 5 )
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date validityPeriod;//有效期

    @ExcelColumn( value = "库存警告值", col = 9)
    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = true, nullable = false)
    private Integer stockWarn;

    private Integer isWarning;

    @ExcelColumn( value = "固定库存", col = 10)
    @ColumnCheckAnnotation(index = 8, length = 64, isRepeat = true, nullable = false)
    private Integer normalStock;

    private Integer stockNum;//此为normalStock与stock的差值

    //以下字段是为了记录对应字典表的ID，在导出时翻译高棉语时使用
    private Long prescriptionId;
    private Long mentalMedicineId;
    private Long levelId;
    private Long medicalInsuranceSignId;
    private Long placeoriginId;
    private Long baseMedicineId;
    private Long narcoticDrugsId;
    private Long disableId;
    private Long isObtainedId;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdName")
    private Long departmentId;
    private String departmentIdName;

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public void setHighCount(Integer highCount) {
        this.highCount = highCount;
    }

    public Integer getNormalStock() {
        return normalStock;
    }

    public void setNormalStock(Integer normalStock) {
        this.normalStock = normalStock;
    }

    public Integer getIsWarning() {
        return isWarning;
    }

    public void setIsWarning(Integer isWarning) {
        this.isWarning = isWarning;
    }

    public Integer getStockWarn() {
        return stockWarn;
    }

    public void setStockWarn(Integer stockWarn) {
        this.stockWarn = stockWarn;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getEnterPrice() {
        return enterPrice;
    }

    public void setEnterPrice(BigDecimal enterPrice) {
        this.enterPrice = enterPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSaleCounts() {
        return saleCounts;
    }

    public void setSaleCounts(Integer saleCounts) {
        this.saleCounts = saleCounts;
    }

    public Integer getIsObtained() {
        return isObtained;
    }

    public void setIsObtained(Integer isObtained) {
        this.isObtained = isObtained;
    }

    public String getObtained() {
        return obtained;
    }

    public void setObtained(String obtained) {
        this.obtained = obtained;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getPlaceorigin() {
        return placeorigin;
    }

    public void setPlaceorigin(Integer placeorigin) {
        this.placeorigin = placeorigin;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getPrecriptionName() {
        return precriptionName;
    }

    public void setPrecriptionName(String precriptionName) {
        this.precriptionName = precriptionName;
    }


    public String getMentalmedicine() {
        return mentalmedicine;
    }

    public void setMentalmedicine(String mentalmedicine) {
        this.mentalmedicine = mentalmedicine;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getLeveld() {
        return leveld;
    }

    public void setLeveld(String leveld) {
        this.leveld = leveld;
    }

    public String getMedicalinsurancesign() {
        return medicalinsurancesign;
    }

    public void setMedicalinsurancesign(String medicalinsurancesign) {
        this.medicalinsurancesign = medicalinsurancesign;
    }

    public String getBasemedicine() {
        return basemedicine;
    }

    public void setBasemedicine(String basemedicine) {
        this.basemedicine = basemedicine;
    }

    public String getNarcoticdrugs() {
        return narcoticdrugs;
    }

    public void setNarcoticdrugs(String narcoticdrugs) {
        this.narcoticdrugs = narcoticdrugs;
    }

    public String getPlaceoriginName() {
        return placeoriginName;
    }

    public void setPlaceoriginName(String placeoriginName) {
        this.placeoriginName = placeoriginName;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public Integer getLowCount() {
        return lowCount;
    }

    public void setLowCount(Integer lowCount) {
        this.lowCount = lowCount;
    }

    public Integer getHighCount() {
        return highCount;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Long getMentalMedicineId() {
        return mentalMedicineId;
    }

    public void setMentalMedicineId(Long mentalMedicineId) {
        this.mentalMedicineId = mentalMedicineId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getMedicalInsuranceSignId() {
        return medicalInsuranceSignId;
    }

    public void setMedicalInsuranceSignId(Long medicalInsuranceSignId) {
        this.medicalInsuranceSignId = medicalInsuranceSignId;
    }

    public Long getPlaceoriginId() {
        return placeoriginId;
    }

    public void setPlaceoriginId(Long placeoriginId) {
        this.placeoriginId = placeoriginId;
    }

    public Long getBaseMedicineId() {
        return baseMedicineId;
    }

    public void setBaseMedicineId(Long baseMedicineId) {
        this.baseMedicineId = baseMedicineId;
    }

    public Long getNarcoticDrugsId() {
        return narcoticDrugsId;
    }

    public void setNarcoticDrugsId(Long narcoticDrugsId) {
        this.narcoticDrugsId = narcoticDrugsId;
    }

    public Long getDisableId() {
        return disableId;
    }

    public void setDisableId(Long disableId) {
        this.disableId = disableId;
    }

    public Long getIsObtainedId() {
        return isObtainedId;
    }

    public void setIsObtainedId(Long isObtainedId) {
        this.isObtainedId = isObtainedId;
    }
}