package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisInspectionCombination extends BaseEntity {

    private Long id;

    private Date createDate;

    private Date updateDate;

    private Integer ordernum;

    private String name;

    private String number;

    private String description;

    private String record;

    private BigDecimal money;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "isEnableType")
    private Integer isEnable;
    private String isEnableType;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "assit_project_type", typeName = "assitTypeName")
    private Short type;
    private String assitTypeName;

    //以下字段是为了查看医嘱模板中项目明细
    private Long combineId;

    private String isusable;
    private BigDecimal price;
    private String pinyinCode;
    private String unit;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getAssitTypeName() {
        return assitTypeName;
    }

    public void setAssitTypeName(String assitTypeName) {
        this.assitTypeName = assitTypeName;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsEnableType() {
        return isEnableType;
    }

    public void setIsEnableType(String isEnableType) {
        this.isEnableType = isEnableType;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    @Override
    public String toString() {
        return "HisInspectionCombination{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", ordernum=" + ordernum +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", record='" + record + '\'' +
                ", money=" + money +
                ", isEnable=" + isEnable +
                ", isEnableType='" + isEnableType + '\'' +
                '}';
    }

    public Long getCombineId() {
        return combineId;
    }

    public void setCombineId(Long combineId) {
        this.combineId = combineId;
    }

    public String getIsusable() {
        return isusable;
    }

    public void setIsusable(String isusable) {
        this.isusable = isusable;
    }
}