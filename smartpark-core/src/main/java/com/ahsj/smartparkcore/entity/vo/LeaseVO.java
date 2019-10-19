package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.Lease;
import core.code.CodeValueColumn;
import core.code.Constants;

import java.math.BigDecimal;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LeaseVO
 * <p>
 * Date:     2019/10/19 16:41
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public class LeaseVO extends Lease {

    private Long id;

    private String location;

    private BigDecimal price;

    private Double area;

    private String phoneNumber;

    private String description;

    private String capacity;

    private Integer isLease;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "book_type", typeName = "bookTypeName")
    private Integer bookType;
    private String bookTypeName;

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
