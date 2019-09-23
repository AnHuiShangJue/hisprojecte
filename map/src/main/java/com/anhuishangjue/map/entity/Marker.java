package com.anhuishangjue.map.entity;

import java.util.Date;

public class Marker {
    private Long id;

    private String x;

    private String y;

    private String offsetX;

    private String offsetY;

    private String icon;

    private String title;

    private String imageOffsetX;

    private String imageOffsetY;

    private Long createId;

    private Date createTime;

    private Long updateId;

    private Date updateTime;

    private Byte visible;

    private String animation;

    private Byte clickable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }

    public String getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(String offsetX) {
        this.offsetX = offsetX == null ? null : offsetX.trim();
    }

    public String getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(String offsetY) {
        this.offsetY = offsetY == null ? null : offsetY.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageOffsetX() {
        return imageOffsetX;
    }

    public void setImageOffsetX(String imageOffsetX) {
        this.imageOffsetX = imageOffsetX == null ? null : imageOffsetX.trim();
    }

    public String getImageOffsetY() {
        return imageOffsetY;
    }

    public void setImageOffsetY(String imageOffsetY) {
        this.imageOffsetY = imageOffsetY == null ? null : imageOffsetY.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getVisible() {
        return visible;
    }

    public void setVisible(Byte visible) {
        this.visible = visible;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation == null ? null : animation.trim();
    }

    public Byte getClickable() {
        return clickable;
    }

    public void setClickable(Byte clickable) {
        this.clickable = clickable;
    }
}