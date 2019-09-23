package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisInspecProject extends BaseEntity {

    private BigDecimal priceNum;

    private Long id;

    private Date createDate;

    private Date updateDate;

    private Integer orderNum;

    private Long projectId;

    private Long inspectionId;

    private Integer num;

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


    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPriceNum() {
        return priceNum;
    }

    public void setPriceNum(BigDecimal priceNum) {
        this.priceNum = priceNum;
    }
}