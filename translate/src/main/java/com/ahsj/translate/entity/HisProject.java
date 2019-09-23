package com.ahsj.translate.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisProject extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2302183663270477751L;
    private Long projectId ;

    private  Long combinationId;

    private  Long inspectionId;

    private Long id;

    private Date createDate;

    private Date updateDate;

    private String name;

    private String description;

    private Integer num;

    private String deptName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "assit_project_type", typeName = "assitTypeName")
    private Short type;
    private String assitTypeName;


    private BigDecimal price;

    private Long number;

    private String pinyinCode;

    private String unit;

    private Long departmentId;

    private String combinationName;

    private String combinationNumber;

    private Integer hisProjectNum;

     private  Integer hisProjectOrderNum ;



    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "disable", typeName = "isEnableType")
    private Integer isEnable;
    private String isEnableType;


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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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
                '}';
    }
}