package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: EnterpriseInfoDTO
 * <p>
 * Date:     2019/10/11 16:19
 *
 * @author XJP
 * @create 2019/10/11
 * @since 1.0.0
 */

public class EnterpriseInfoDTO  extends EnterpriseInfo {

    private String idCard;

    private String phone;

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "EnterpriseInfoDTO{" +
                "idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
