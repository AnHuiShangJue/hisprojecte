package com.ahsj.translate.common.dto;


import com.ahsj.translate.entity.*;

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


    private static final long serialVersionUID = -110686659254668134L;


    HisProject hisProject;

    HisMedicineInfo hisMedicineInfo;

    HisMedicationDetails hisMedicationDetails;

    HisAnkleTemplate hisAnkleTemplate;

    HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails;

    HisConsumablesBuyplan hisConsumablesBuyplan;

    HisConsumablesDetails hisConsumablesDetails;

    List<Organization> organizationList;

    SysCodeDetail sysCodeDetail;

    HisConsumables hisConsumables;

    OrganizationModel organizationModel;

    List<OrganizationModel> organizationModelList;

    SysCode sysCode;

    Long userId;

    List<SysCodeDetail> sysCodeDetailList;


    public MqObjectModel() {
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<SysCodeDetail> getSysCodeDetailList() {
        return sysCodeDetailList;
    }

    public void setSysCodeDetailList(List<SysCodeDetail> sysCodeDetailList) {
        this.sysCodeDetailList = sysCodeDetailList;
    }

    public SysCodeDetail getSysCodeDetail() {
        return sysCodeDetail;
    }

    public void setSysCodeDetail(SysCodeDetail sysCodeDetail) {
        this.sysCodeDetail = sysCodeDetail;
    }

    public SysCode getSysCode() {
        return sysCode;
    }

    public void setSysCode(SysCode sysCode) {
        this.sysCode = sysCode;
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

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
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

    public List<OrganizationModel> getOrganizationModelList() {
        return organizationModelList;
    }

    public void setOrganizationModelList(List<OrganizationModel> organizationModelList) {
        this.organizationModelList = organizationModelList;
    }
}
