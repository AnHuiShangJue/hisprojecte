package com.ahsj.translate.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class Translate extends BaseEntity {
    private Long id;

    private Long translateId;

    private String translateType;

    private String translateChina;

    private String translateKhmer;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    private String translateJoin;

    private String translateJoin2;

    private String translateJoin3;

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

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
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
        return "Translate{" +
                "id=" + id +
                ", translateId=" + translateId +
                ", translateType='" + translateType + '\'' +
                ", translateChina='" + translateChina + '\'' +
                ", translateKhmer='" + translateKhmer + '\'' +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", updateUserId=" + updateUserId +
                ", updateDate=" + updateDate +
                ", translateJoin='" + translateJoin + '\'' +
                ", translateColumn='" + translateColumn + '\'' +
                '}';
    }

    public String getTranslateJoin2() {
        return translateJoin2;
    }

    public void setTranslateJoin2(String translateJoin2) {
        this.translateJoin2 = translateJoin2;
    }

    public String getTranslateJoin3() {
        return translateJoin3;
    }

    public void setTranslateJoin3(String translateJoin3) {
        this.translateJoin3 = translateJoin3;
    }
}