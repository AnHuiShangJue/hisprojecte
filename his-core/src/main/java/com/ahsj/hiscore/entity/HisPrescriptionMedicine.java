package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisPrescriptionMedicine extends BaseEntity {
    @ExcelColumn( value = "序号", col = 1)
    private Long id;

    private String treeId;

    private String parentId;

    private String prescriptionId;

    private Long medicineId;

    @ExcelColumn( value = "用法用量", col = 9)
    @ColumnCheckAnnotation(index = 14, length = 64, isRepeat = true, nullable = false)
    private String description;

    @ExcelColumn( value = "药品编号", col = 5)
    private String drugsNumb;

    @ExcelColumn( value = "药品名称", col = 6)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private String drugsName;

    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = true, nullable = false)
    private String drugsAlias;

    @ExcelColumn( value = "药品规格", col = 7)
    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false)
    private String drugsSpec;

    @ColumnCheckAnnotation(index = 8, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "prescription")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "prescription", typeName = "prescriptionName")
    private Integer prescription;
    private String prescriptionName;

    @ColumnCheckAnnotation(index = 9, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "mental_medicine")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "mental_medicine", typeName = "mentalMedicineName")
    private Integer mentalMedicine;
    private String mentalMedicineName;

    private String largeUnit;

    private String smallUnit;

    private Short conversionRate;

    @ColumnCheckAnnotation(index = 10, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "disable")
    private Integer disable;
    private String disableName;

    @ColumnCheckAnnotation(index = 6, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "level")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "level", typeName = "levelName")
    private Integer level;
    private String levelName;

    @ColumnCheckAnnotation(index = 7, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "medical_insurance_sign")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "medical_insurance_sign", typeName = "medicalInsuranceSignName")
    private Integer medicalInsuranceSign;
    private String medicalInsuranceSignName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "placeOrigin", typeName = "placeoriginName")
    private Integer placeorigin;
    private String placeoriginName;

    @ColumnCheckAnnotation(index = 11, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "base_medicine")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "base_medicine", typeName = "baseMedicineName")
    private Integer baseMedicine;
    private String baseMedicineName;

    @ColumnCheckAnnotation(index = 12, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "narcotic_drugs")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "narcotic_drugs", typeName = "narcoticDrugsName")
    private Integer narcoticDrugs;
    private String narcoticDrugsName;

    private String remarks;

    private Date createDate;

    private Date updateDate;

    private Long pharmacyId;

    @ExcelColumn( value = "用药数量", col = 8)
    @ColumnCheckAnnotation(index = 13, length = 64, isRepeat = true, nullable = false)
    private Integer count;

    /*导出翻译需要的字段*/
    private Long drugsId;

    private Long levelId;

    private Long signId;

    private Long disableId;

    private Long preId;

    private Long mentalMedicineId;

    private Long baseMedicineId;

    private Long narcoticDrugsId;

    private Long[] ids;

    private Long pId;

    //以下字段用于药方excel导入导出
    @ExcelColumn( value = "药方父菜单", col = 2)
    private String parentMenu;//药方父菜单名称

    @ExcelColumn( value = "药方名称", col = 3)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private String name;//药方名称

    @ExcelColumn( value = "药方描述", col = 4)
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false)
    private String descrption;//药方描述

    //以下字段用于显示药方中药品是否足够，下架停用等相关信息
    private String isenough;

    private String isusable;

    private String isObtainedName;

    public String getIsenough() {
        return isenough;
    }

    public void setIsenough(String isenough) {
        this.isenough = isenough;
    }

    public String getIsusable() {
        return isusable;
    }

    public void setIsusable(String isusable) {
        this.isusable = isusable;
    }

    public String getIsObtainedName() {
        return isObtainedName;
    }

    public void setIsObtainedName(String isObtainedName) {
        this.isObtainedName = isObtainedName;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Long getDrugsId() {
        return drugsId;
    }

    public void setDrugsId(Long drugsId) {
        this.drugsId = drugsId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Long getDisableId() {
        return disableId;
    }

    public void setDisableId(Long disableId) {
        this.disableId = disableId;
    }

    public Long getPreId() {
        return preId;
    }

    public void setPreId(Long preId) {
        this.preId = preId;
    }

    public Long getMentalMedicineId() {
        return mentalMedicineId;
    }

    public void setMentalMedicineId(Long mentalMedicineId) {
        this.mentalMedicineId = mentalMedicineId;
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

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getMentalMedicineName() {
        return mentalMedicineName;
    }

    public void setMentalMedicineName(String mentalMedicineName) {
        this.mentalMedicineName = mentalMedicineName;
    }

    public String getDisableName() {
        return disableName;
    }

    public void setDisableName(String disableName) {
        this.disableName = disableName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getMedicalInsuranceSignName() {
        return medicalInsuranceSignName;
    }

    public void setMedicalInsuranceSignName(String medicalInsuranceSignName) {
        this.medicalInsuranceSignName = medicalInsuranceSignName;
    }

    public String getBaseMedicineName() {
        return baseMedicineName;
    }

    public void setBaseMedicineName(String baseMedicineName) {
        this.baseMedicineName = baseMedicineName;
    }

    public String getNarcoticDrugsName() {
        return narcoticDrugsName;
    }

    public void setNarcoticDrugsName(String narcoticDrugsName) {
        this.narcoticDrugsName = narcoticDrugsName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId == null ? null : treeId.trim();
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId == null ? null : prescriptionId.trim();
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPlaceoriginName() {
        return placeoriginName;
    }

    public void setPlaceoriginName(String placeoriginName) {
        this.placeoriginName = placeoriginName;
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }
}