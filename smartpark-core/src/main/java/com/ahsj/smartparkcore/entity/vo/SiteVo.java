package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.Site;

import java.math.BigDecimal;

public class SiteVo extends Site {

    private Long provinceId;

    private Long cityId;

    private Long areaId;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}