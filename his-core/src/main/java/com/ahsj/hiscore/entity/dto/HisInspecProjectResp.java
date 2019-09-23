package com.ahsj.hiscore.entity.dto;

import java.math.BigDecimal;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisInspecProjectResp
 * <p>
 * Date:     2019/7/13 17:24
 *
 * @author XJP
 * @create 2019/7/13
 * @since 1.0.0
 */

public class HisInspecProjectResp {

    private Long projectId;

    private String projectName;

    private BigDecimal projectPrice;

    private String projectUnit;

    private Long projectNumber;

    public HisInspecProjectResp() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getProjectUnit() {
        return projectUnit;
    }

    public void setProjectUnit(String projectUnit) {
        this.projectUnit = projectUnit;
    }

    public Long getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Long projectNumber) {
        this.projectNumber = projectNumber;
    }
}
