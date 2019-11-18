package com.ahsj.wis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Sys {
    private Long id;

    private String name;

    private String url;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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