package com.ahsj.hiscore.entity.dto;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.excel.POExcelImportAnnotation;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisProjectRes
 * <p>
 * Date:     2019/8/28 12:27
 *
 * @author XJP
 * @create 2019/8/28
 * @since 1.0.0
 */

@POExcelImportAnnotation(startRowIndex = 1)
public class HisProjectRes {

    @ExcelColumn(value = "序号", col = 1)
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    @ExcelColumn(value = "项目名称", col = 2)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    private String name;

    @ExcelColumn(value = "所属科室", col = 3)
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false)
    private String deptName;

    @ExcelColumn(value = "项目类型", col = 4)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private String assitTypeName;

    @ExcelColumn(value = "项目收费标准", col = 5)
    @ColumnCheckAnnotation(index = 4, length = 64,dataType = DataType.Bigdecimal, isRepeat = true, nullable = false)
    private BigDecimal price;

    @ExcelColumn(value = "项目的拼音码", col = 6)
    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false)
    private String pinyinCode;

    @ExcelColumn(value = "项目的使用单位", col = 7)
    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = true, nullable = false)
    private String unit;

    private Integer isEnable;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAssitTypeName() {
        return assitTypeName;
    }

    public void setAssitTypeName(String assitTypeName) {
        this.assitTypeName = assitTypeName;
    }

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
}
