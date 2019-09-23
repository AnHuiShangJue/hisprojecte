package com.ahsj.hiscore.entity.dto;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: MqObjectModel
 * <p>
 * Date:     2019/7/29 9:58
 *
 * @author XJP
 * @create 2019/7/29
 * @since 1.0.0
 */

public class MqObjectModel implements Serializable {


    private static final long serialVersionUID = 9165607028001008126L;


    HisProject hisProject;

    List<OrganizationModel> organizationModelList;

    List<SysCodeDetail> sysCodeDetailList;

    HisMedicineInfo hisMedicineInfo;

    HisMedicationDetails hisMedicationDetails;

    HisAnkleTemplate hisAnkleTemplate;

    HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails;

    HisConsumablesBuyplan hisConsumablesBuyplan;

    HisConsumablesDetails hisConsumablesDetails;

    HisConsumables hisConsumables;

    List<Organization> organizationList;

    Long userId;


    public MqObjectModel() {
    }

    public HisProject getHisProject() {
        return hisProject;
    }

    public void setHisProject(HisProject hisProject) {
        this.hisProject = hisProject;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public HisMedicineInfo getHisMedicineInfo() {
        return hisMedicineInfo;
    }

    public void setHisMedicineInfo(HisMedicineInfo hisMedicineInfo) {
        this.hisMedicineInfo = hisMedicineInfo;
    }

    public HisMedicationDetails getHisMedicationDetails() {
        return hisMedicationDetails;
    }

    public void setHisMedicationDetails(HisMedicationDetails hisMedicationDetails) {
        this.hisMedicationDetails = hisMedicationDetails;
    }

    public HisAnkleTemplate getHisAnkleTemplate() {
        return hisAnkleTemplate;
    }

    public void setHisAnkleTemplate(HisAnkleTemplate hisAnkleTemplate) {
        this.hisAnkleTemplate = hisAnkleTemplate;
    }

    public HisConsumablesBuyplanDetails getHisConsumablesBuyplanDetails() {
        return hisConsumablesBuyplanDetails;
    }

    public void setHisConsumablesBuyplanDetails(HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails) {
        this.hisConsumablesBuyplanDetails = hisConsumablesBuyplanDetails;
    }

    public HisConsumablesBuyplan getHisConsumablesBuyplan() {
        return hisConsumablesBuyplan;
    }

    public void setHisConsumablesBuyplan(HisConsumablesBuyplan hisConsumablesBuyplan) {
        this.hisConsumablesBuyplan = hisConsumablesBuyplan;
    }

    public HisConsumablesDetails getHisConsumablesDetails() {
        return hisConsumablesDetails;
    }

    public void setHisConsumablesDetails(HisConsumablesDetails hisConsumablesDetails) {
        this.hisConsumablesDetails = hisConsumablesDetails;
    }

    public HisConsumables getHisConsumables() {
        return hisConsumables;
    }

    public void setHisConsumables(HisConsumables hisConsumables) {
        this.hisConsumables = hisConsumables;
    }

    public List<SysCodeDetail> getSysCodeDetailList() {
        return sysCodeDetailList;
    }

    public void setSysCodeDetailList(List<SysCodeDetail> sysCodeDetailList) {
        this.sysCodeDetailList = sysCodeDetailList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<OrganizationModel> getOrganizationModelList() {
        return organizationModelList;
    }

    public void setOrganizationModelList(List<OrganizationModel> organizationModelList) {
        this.organizationModelList = organizationModelList;
    }
}
