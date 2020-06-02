package com.ahsj.hiscore.entity.dto;

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

/**
 * @ClassName : MedicationExportExcel
 * @Description :
 * @Author : XJP
 * @Date: 2020-05-29 16:05
 */
public class MedicationExportExcel extends BaseEntity {

    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    @ColumnCheckAnnotation(index = 9, length = 64, isRepeat = false, nullable = false)
    private Integer drugsNumbCount;//数量

    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = false, nullable = false)
    private String drugsNumb;//药品编号

    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = false, nullable = false)
    private String drugsName;//药品名称

    @ColumnCheckAnnotation(index = 10, length = 64, isRepeat = false, nullable = false)
    private BigDecimal enterPrice;//进价

    @ColumnCheckAnnotation(index = 8, length = 64, isRepeat = false, nullable = false)
    private BigDecimal salePrice;//售价

    @ColumnCheckAnnotation(index = 11, length = 64, isRepeat = false, nullable = false)
    private BigDecimal priceSum;//总价

    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = false, nullable = false)
    private String drugsSpec;//药品规格

    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = false, nullable = false)
    private String largeUnit;//大单位

    private String smallUnit;//小单位

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentName")
    private Long departmentId;
    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = false, nullable = false)
    private String departmentName;


//    @ColumnCheckAnnotation(index = 8, length = 64, isRepeat = false, nullable = false)

    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "placeOrigin")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "placeOrigin", typeName = "placeOriginName")
    private Integer placeOrigin;//原产地
    private String placeOriginName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, dateFormat = "yyyy-MM-dd", nullable = false)
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date highTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date lowTime;

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb;
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName;
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

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    public Integer getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(Integer placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec;
    }

    public String getLargeUnit() {
        return largeUnit;
    }

    public void setLargeUnit(String largeUnit) {
        this.largeUnit = largeUnit;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDrugsNumbCount() {
        return drugsNumbCount;
    }

    public void setDrugsNumbCount(Integer drugsNumbCount) {
        this.drugsNumbCount = drugsNumbCount;
    }

    public String getSmallUnit() {
        return smallUnit;
    }

    public void setSmallUnit(String smallUnit) {
        this.smallUnit = smallUnit;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    public String getPlaceOriginName() {
        return placeOriginName;
    }

    public void setPlaceOriginName(String placeOriginName) {
        this.placeOriginName = placeOriginName;
    }
}
