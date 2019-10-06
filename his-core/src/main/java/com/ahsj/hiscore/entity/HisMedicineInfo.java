package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisMedicineInfo extends BaseEntity {
    @ExcelColumn(value = "序号", col = 1)
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    @ExcelColumn(value = "药品编号", col = 2)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private String drugsNumb;

    @ExcelColumn(value = "药品名称", col = 3)
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false)
    private String drugsName;

    @ExcelColumn(value = "药品别名", col = 4)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private String drugsAlias;

    @ExcelColumn(value = "药品规格", col = 5)
    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = true, nullable = false)
    private String drugsSpec;


    @ColumnCheckAnnotation(index = 5, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "prescription")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "prescription", typeName = "precriptionName")
    private Integer prescription;
    @ExcelColumn(value = "是否为处方药", col = 6)
    private String precriptionName;


    @ColumnCheckAnnotation(index = 6, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "mental_medicine")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "mental_medicine", typeName = "mentalmedicine")
    private Integer mentalMedicine;
    @ExcelColumn(value = "是否为精神药品", col = 7)
    private String mentalmedicine;

    @ExcelColumn(value = "大单位", col = 8)
    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = true, nullable = false)
    private String largeUnit;

    @ExcelColumn(value = "小单位", col = 9)
    @ColumnCheckAnnotation(index = 8, length = 64, isRepeat = true, nullable = false)
    private String smallUnit;

    @ExcelColumn(value = "转换率", col = 10)
    @ColumnCheckAnnotation(index = 9, length = 64, isRepeat = true, nullable = false)
    private Short conversionRate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "disable", typeName = "disabled")
    private Integer disable;
    private String disabled;


    @ColumnCheckAnnotation(index = 10, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "level")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "level", typeName = "leveld")
    private Integer level;
    @ExcelColumn(value = "药品级别", col = 11)
    private String leveld;


    @ColumnCheckAnnotation(index = 11, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "medical_insurance_sign")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "medical_insurance_sign", typeName = "medicalinsurancesign")
    private Integer medicalInsuranceSign;
    @ExcelColumn(value = "药品类型", col = 12)
    private String medicalinsurancesign;


    @ColumnCheckAnnotation(index = 12, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "placeOrigin")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "placeOrigin", typeName = "placeoriginName")
    private Integer placeorigin;
    @ExcelColumn(value = "原产地", col = 13)
    private String placeoriginName;


    @ColumnCheckAnnotation(index = 13, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "base_medicine")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "base_medicine", typeName = "basemedicine")
    private Integer baseMedicine;
    @ExcelColumn(value = "是否为基础药品", col = 14)
    private String basemedicine;


    @ColumnCheckAnnotation(index = 14, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "narcotic_drugs")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "narcotic_drugs", typeName = "narcoticdrugs")
    private Integer narcoticDrugs;
    @ExcelColumn(value = "是否为麻醉药品", col = 15)
    private String narcoticdrugs;

    @ExcelColumn(value = "备注", col = 16)
    @ColumnCheckAnnotation(index = 15, length = 64, isRepeat = true, nullable = false)
    private String remarks;

    private String createUserId;

    private Date createDate;

    private String updateUserId;

    private Date updateDate;

    @ExcelColumn(value = "药品进价", col = 17)
    @ColumnCheckAnnotation(index = 16, length = 64, isRepeat = true, nullable = false)
    private Double enterPrice;

    //以下字段是为了记录对应字典表的ID，在导出时翻译高棉语时使用
    private Long prescriptionId;
    private Long mentalMedicineId;
    private Long levelId;
    private Long medicalInsuranceSignId;
    private Long placeoriginId;
    private Long baseMedicineId;
    private Long narcoticDrugsId;

    private String tdrugsSpec;

    private String translateKhmer;

    private String chineseEnglishName;

    public String getTranslateKhmer() {
        return translateKhmer;
    }

    public void setTranslateKhmer(String translateKhmer) {
        this.translateKhmer = translateKhmer;
    }

    public String getChineseEnglishName() {
        return chineseEnglishName;
    }

    public void setChineseEnglishName(String chineseEnglishName) {
        this.chineseEnglishName = chineseEnglishName;
    }

    public String getTdrugsSpec() {
        return tdrugsSpec;
    }

    public void setTdrugsSpec(String tdrugsSpec) {
        this.tdrugsSpec = tdrugsSpec;
    }

    public Double getEnterPrice() {
        return enterPrice;
    }

    public void setEnterPrice(Double enterPrice) {
        this.enterPrice = enterPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Override
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
}