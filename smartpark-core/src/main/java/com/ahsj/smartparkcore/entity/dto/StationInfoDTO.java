package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.StationInfo;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: StationInfoDTO
 * <p>
 * Date:     2019/10/19 18:09
 *
 * @author XJP
 * @create 2019/10/19
 * @since 1.0.0
 */

public class StationInfoDTO extends StationInfo {

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
