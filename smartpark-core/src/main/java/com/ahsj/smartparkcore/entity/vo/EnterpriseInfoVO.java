package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: EnterpriseInfoVO
 * <p>
 * Date:     2019/10/11 16:17
 *
 * @author XJP
 * @create 2019/10/11
 * @since 1.0.0
 */

public class EnterpriseInfoVO  extends EnterpriseInfo {

    private String idCard;

    private String phone;

    private String provinceName;

    private String cityName;

    private String areaName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
}
