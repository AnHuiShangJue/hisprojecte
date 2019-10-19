package com.ahsj.wxapplet.entity;

import java.io.Serializable;

/**
 * @program: his
 * @description:SelectEntity
 * @author: chenzhicai
 * @create: 2019-06-21-13-18
 **/
public class SelectEntity implements Serializable {
    private static final long serialVersionUID = -1510338447858671826L;
    private String value;
    private String name;
    private Integer index;
    private String nested;
    private boolean selected;

    public SelectEntity() {
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getNested() {
        return this.nested;
    }

    public void setNested(String nested) {
        this.nested = nested;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

    