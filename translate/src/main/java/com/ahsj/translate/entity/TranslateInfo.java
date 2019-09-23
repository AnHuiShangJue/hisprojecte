package com.ahsj.translate.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class TranslateInfo extends BaseEntity {
    private Long id;

    private Long translateId;

    private String translateType;

    private String translateChina;

    private String translateKhmer;


    private Date createDate;


    private Date updateDate;

    private String translateJoin;

    private String translateColumn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTranslateId() {
        return translateId;
    }

    public void setTranslateId(Long translateId) {
        this.translateId = translateId;
    }

    public String getTranslateType() {
        return translateType;
    }

    public void setTranslateType(String translateType) {
        this.translateType = translateType == null ? null : translateType.trim();
    }

    public String getTranslateChina() {
        return translateChina;
    }

    public void setTranslateChina(String translateChina) {
        this.translateChina = translateChina == null ? null : translateChina.trim();
    }

    public String getTranslateKhmer() {
        return translateKhmer;
    }

    public void setTranslateKhmer(String translateKhmer) {
        this.translateKhmer = translateKhmer == null ? null : translateKhmer.trim();
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

    public String getTranslateJoin() {
        return translateJoin;
    }

    public void setTranslateJoin(String translateJoin) {
        this.translateJoin = translateJoin == null ? null : translateJoin.trim();
    }

    public String getTranslateColumn() {
        return translateColumn;
    }

    public void setTranslateColumn(String translateColumn) {
        this.translateColumn = translateColumn == null ? null : translateColumn.trim();
    }

    @Override
    public String toString() {
        return "TranslateInfo{" +
                "id=" + id +
                ", translateId=" + translateId +
                ", translateType='" + translateType + '\'' +
                ", translateChina='" + translateChina + '\'' +
                ", translateKhmer='" + translateKhmer + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", translateJoin='" + translateJoin + '\'' +
                ", translateColumn='" + translateColumn + '\'' +
                '}';
    }
}