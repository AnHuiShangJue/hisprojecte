package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.Lease;
import core.code.CodeValueColumn;
import core.code.Constants;

import java.math.BigDecimal;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LeaseDTO
 * <p>
 * Date:     2019/10/19 16:41
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public class LeaseDTO extends Lease {

    private Long id;

    private String location;

    private String name;

    private BigDecimal price;

    private Double area;

    private String phoneNumber;

    private String description;

    private String capacity;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "isLeaseName")
    private Integer isLease;
    private String isLeaseName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "book_type", typeName = "bookTypeName")
    private Integer bookType;
    private String bookTypeName;

    private BigDecimal upPrice;//上区间

    private BigDecimal lowPrice;//下区间

    public BigDecimal getUpPrice() {
        return upPrice;
    }

    public void setUpPrice(BigDecimal upPrice) {
        this.upPrice = upPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Double getArea() {
        return area;
    }

    @Override
    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public String getIsLeaseName() {
        return isLeaseName;
    }

    public void setIsLeaseName(String isLeaseName) {
        this.isLeaseName = isLeaseName;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }
}
