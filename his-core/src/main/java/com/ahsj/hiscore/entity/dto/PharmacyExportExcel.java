package com.ahsj.hiscore.entity.dto;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName : PharmacyExportExcel
 * @Description :
 * @Author : XJP
 * @Date: 2020-06-01 14:28
 */
public class PharmacyExportExcel extends BaseEntity {

    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    @ColumnCheckAnnotation(index = 8, length = 64, isRepeat = false, nullable = false)
    private Integer stock;//数量

    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = false, nullable = false)
    private String drugsNumb;//药品编号

    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = false, nullable = false)
    private String drugsName;//药品名称

    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = false, nullable = false)
    private BigDecimal salePrice;//售价

    @ColumnCheckAnnotation(index = 9, length = 64, isRepeat = false, nullable = false)
    private BigDecimal priceSum;//总价

    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = false, nullable = false)
    private String drugsSpec;//药品规格

    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = false, nullable = false)
    private String largeUnit;//大单位

    private String smallUnit;//小单位

    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "placeOrigin")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "placeOrigin", typeName = "placeOriginName")
    private Integer placeOrigin;//原产地
    private String placeOriginName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, dateFormat = "yyyy-MM-dd", nullable = false)
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSmallUnit() {
        return smallUnit;
    }

    public void setSmallUnit(String smallUnit) {
        this.smallUnit = smallUnit;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
