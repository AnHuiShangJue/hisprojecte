package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.StationInfo;

import java.math.BigDecimal;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoVO
 * <p>
 * Date:     2019/10/19 18:09
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public class StationInfoVO extends StationInfo {

    private Long provinceId;

    private Long cityId;

    private Long areaId;

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
