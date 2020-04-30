package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisConsumables extends BaseEntity {
    @ExcelColumn( value = "序号", col = 1)
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    @ExcelColumn( value = "耗材名称", col = 2)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private String name;

    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false)
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "consumable_type", typeName = "typeName")
    private Integer type;
    @ExcelColumn( value = "耗材类型", col = 3)
    private String typeName;

    @ExcelColumn( value = "耗材规格", col = 4)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private String spec;


    private Integer stock;

    @ExcelColumn( value = "价格", col = 5)
    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = true, nullable = false)
    private Double price;

    private Double lowPrice;
    private Double highPrice;


    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false)
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enable")
    private Integer isEnable;
    @ExcelColumn( value = "是否可用", col = 6)
    private String enable;

    private String consumablesCode;

    private Integer isDelete;

    //字典
    private Long typeId;
    private Long enableId;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getConsumablesCode() {
        return consumablesCode;
    }

    public void setConsumablesCode(String consumablesCode) {
        this.consumablesCode = consumablesCode;
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



    public Integer getIsEnable() {
        return isEnable;
    }


    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getEnableId() {
        return enableId;
    }

    public void setEnableId(Long enableId) {
        this.enableId = enableId;
    }
}