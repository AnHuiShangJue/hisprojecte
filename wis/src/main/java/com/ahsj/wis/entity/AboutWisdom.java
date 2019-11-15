package com.ahsj.wis.entity;

import java.util.Date;

public class AboutWisdom {
    private Integer id;

    private String introduce;

    private String shui;

    private String shuiP;

    private String shuiIntroduce;

    private String han;

    private String hanP;

    private String hanIntroduce;

    private String yao;

    private String yaoP;

    private String yaoIntroduce;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getShui() {
        return shui;
    }

    public void setShui(String shui) {
        this.shui = shui == null ? null : shui.trim();
    }

    public String getShuiP() {
        return shuiP;
    }

    public void setShuiP(String shuiP) {
        this.shuiP = shuiP == null ? null : shuiP.trim();
    }

    public String getShuiIntroduce() {
        return shuiIntroduce;
    }

    public void setShuiIntroduce(String shuiIntroduce) {
        this.shuiIntroduce = shuiIntroduce == null ? null : shuiIntroduce.trim();
    }

    public String getHan() {
        return han;
    }

    public void setHan(String han) {
        this.han = han == null ? null : han.trim();
    }

    public String getHanP() {
        return hanP;
    }

    public void setHanP(String hanP) {
        this.hanP = hanP == null ? null : hanP.trim();
    }

    public String getHanIntroduce() {
        return hanIntroduce;
    }

    public void setHanIntroduce(String hanIntroduce) {
        this.hanIntroduce = hanIntroduce == null ? null : hanIntroduce.trim();
    }

    public String getYao() {
        return yao;
    }

    public void setYao(String yao) {
        this.yao = yao == null ? null : yao.trim();
    }

    public String getYaoP() {
        return yaoP;
    }

    public void setYaoP(String yaoP) {
        this.yaoP = yaoP == null ? null : yaoP.trim();
    }

    public String getYaoIntroduce() {
        return yaoIntroduce;
    }

    public void setYaoIntroduce(String yaoIntroduce) {
        this.yaoIntroduce = yaoIntroduce == null ? null : yaoIntroduce.trim();
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
}