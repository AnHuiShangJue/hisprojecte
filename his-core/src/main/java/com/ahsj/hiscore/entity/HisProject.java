package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.excel.POExcelImportAnnotation;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@POExcelImportAnnotation(startRowIndex = 1)
public class HisProject extends BaseEntity implements Serializable {

    private Long sysCodeId;

    private Long projectId;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer sex;
    private String sexName;

    private Long combinationId;

    private Long inspectionId;

    @ExcelColumn( value = "序号", col = 1)
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    private Date createDate;


    @ExcelColumn( value = "项目编号", col = 2)
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false)
    private String number;

    private Date updateDate;

    @ExcelColumn(value = "项目名称", col = 3)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private String name;

    @ExcelColumn(value = "项目描述", col = 4)
    private String description;


    private Integer num;

  // @ExcelColumn( value = "所属科室",col = 4)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private String deptName;

    @ColumnCheckAnnotation(index = 4, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "assit_project_type")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "assit_project_type", typeName = "assitTypeName")
    private Short type;
   // @ExcelColumn( value = "项目类型",col = 4)
    private String assitTypeName;


    @ExcelColumn(value = "项目的使用单位", col = 5)
    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = true, nullable = false)
    private String unit;

    @ExcelColumn(value = "项目收费标准", col = 6)
    @ColumnCheckAnnotation(index = 5, length = 64,dataType = DataType.Bigdecimal, isRepeat = true, nullable = false)
    private BigDecimal price;



 //  @ExcelColumn( value = "项目的拼音码",col = 6)
    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = true, nullable = false)
    private String pinyinCode;



    private Long departmentId;

    private String combinationName;

    private String combinationNumber;

    private Integer hisProjectNum;

    private Integer hisProjectOrderNum;




    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "isEnableType")//is_enable  disable
    private Integer isEnable;
    private String isEnableType;

    private String medicalRecordId;

    private Integer projectNum;

    private String translateKhmer;

    private String chineseEnglishName;

    public String getChineseEnglishName() {
        return chineseEnglishName;
    }

    public void setChineseEnglishName(String chineseEnglishName) {
        this.chineseEnglishName = chineseEnglishName;
    }

    public String getTranslateKhmer() {
        return translateKhmer;
    }

    public void setTranslateKhmer(String translateKhmer) {
        this.translateKhmer = translateKhmer;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Integer getHisProjectOrderNum() {
        return hisProjectOrderNum;
    }

    public void setHisProjectOrderNum(Integer hisProjectOrderNum) {
        this.hisProjectOrderNum = hisProjectOrderNum;
    }

    public Integer getHisProjectNum() {
        return hisProjectNum;
    }

    public void setHisProjectnum(Integer hisProjectNum) {
        this.hisProjectNum = hisProjectNum;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getAssitTypeName() {
        return assitTypeName;
    }

    public void setAssitTypeName(String assitTypeName) {
        this.assitTypeName = assitTypeName;
    }

    public String getIsEnableType() {
        return isEnableType;
    }

    public void setIsEnableType(String isEnableType) {
        this.isEnableType = isEnableType;
    }

    public String getCombinationName() {
        return combinationName;
    }

    public void setCombinationName(String combinationName) {
        this.combinationName = combinationName;
    }

    public String getCombinationNumber() {
        return combinationNumber;
    }

    public void setCombinationNumber(String combinationNumber) {
        this.combinationNumber = combinationNumber;
    }

    public Long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(Long combinationId) {
        this.combinationId = combinationId;
    }

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    public void setHisProjectNum(Integer hisProjectNum) {
        this.hisProjectNum = hisProjectNum;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public Long getSysCodeId() {
        return sysCodeId;
    }

    public void setSysCodeId(Long sysCodeId) {
        this.sysCodeId = sysCodeId;
    }

    @Override
    public String toString() {
        return "HisProject{" +
                "projectId=" + projectId +
                ", combinationId=" + combinationId +
                ", inspectionId=" + inspectionId +
                ", id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", num=" + num +
                ", deptName='" + deptName + '\'' +
                ", type=" + type +
                ", assitTypeName='" + assitTypeName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", pinyinCode='" + pinyinCode + '\'' +
                ", unit='" + unit + '\'' +
                ", departmentId=" + departmentId +
                ", combinationName='" + combinationName + '\'' +
                ", combinationNumber='" + combinationNumber + '\'' +
                ", hisProjectNum=" + hisProjectNum +
                ", hisProjectOrderNum=" + hisProjectOrderNum +
                ", isEnable=" + isEnable +
                ", isEnableType='" + isEnableType + '\'' +
                ", medicalRecordId='" + medicalRecordId + '\'' +
                '}';
    }
}