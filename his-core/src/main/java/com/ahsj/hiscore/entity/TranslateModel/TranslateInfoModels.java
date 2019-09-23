package com.ahsj.hiscore.entity.TranslateModel;

import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCode;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateInfoModels
 * <p>
 * Date:     2019/8/8 19:08
 *
 * @author XJP
 * @create 2019/8/8
 * @since 1.0.0
 */

public class TranslateInfoModels implements Serializable {

    private static final long serialVersionUID = -1400686431134663275L;

    List<HisProjectTranslate> projectTranslateList;

    List<HisMedicineInfoTranslate> hisMedicineInfoTranslates;

    List<HisMedicationDetailsTranslate> medicineInfoTranslates;

    List<HisAnkleTemplateTranslate> hisMedicineInfoTranslateArrayList;

    List<HisConsumablesBuyplanDetailsTranslate> hisMedicineInfoTranslateList;

    List<HisConsumablesBuyplanTranslate> medicineInfoTranslateList;

    List<HisConsumablesDetailsTranslate> medicineInfoTranslateArrayList;

    List<HisConsumablesTranslate> infoTranslateList;

    List<SysCodeDetailTranslate> sysCodeDetailTranslates;

    List<SysCodeTranslate> sysCodeTranslates;

    List<OrganizationTranslate> organizationTranslates;

    Long userId;

    public TranslateInfoModels() {
    }

    public List<SysCodeDetailTranslate> getSysCodeDetailTranslates() {
        return sysCodeDetailTranslates;
    }

    public void setSysCodeDetailTranslates(List<SysCodeDetailTranslate> sysCodeDetailTranslates) {
        this.sysCodeDetailTranslates = sysCodeDetailTranslates;
    }

    public List<SysCodeTranslate> getSysCodeTranslates() {
        return sysCodeTranslates;
    }

    public void setSysCodeTranslates(List<SysCodeTranslate> sysCodeTranslates) {
        this.sysCodeTranslates = sysCodeTranslates;
    }

    public List<OrganizationTranslate> getOrganizationTranslates() {
        return organizationTranslates;
    }

    public void setOrganizationTranslates(List<OrganizationTranslate> organizationTranslates) {
        this.organizationTranslates = organizationTranslates;
    }

    public List<HisProjectTranslate> getProjectTranslateList() {
        return projectTranslateList;
    }

    public void setProjectTranslateList(List<HisProjectTranslate> projectTranslateList) {
        this.projectTranslateList = projectTranslateList;
    }

    public List<HisMedicineInfoTranslate> getHisMedicineInfoTranslates() {
        return hisMedicineInfoTranslates;
    }

    public void setHisMedicineInfoTranslates(List<HisMedicineInfoTranslate> hisMedicineInfoTranslates) {
        this.hisMedicineInfoTranslates = hisMedicineInfoTranslates;
    }

    public List<HisMedicationDetailsTranslate> getMedicineInfoTranslates() {
        return medicineInfoTranslates;
    }

    public void setMedicineInfoTranslates(List<HisMedicationDetailsTranslate> medicineInfoTranslates) {
        this.medicineInfoTranslates = medicineInfoTranslates;
    }

    public List<HisAnkleTemplateTranslate> getHisMedicineInfoTranslateArrayList() {
        return hisMedicineInfoTranslateArrayList;
    }

    public void setHisMedicineInfoTranslateArrayList(List<HisAnkleTemplateTranslate> hisMedicineInfoTranslateArrayList) {
        this.hisMedicineInfoTranslateArrayList = hisMedicineInfoTranslateArrayList;
    }

    public List<HisConsumablesBuyplanDetailsTranslate> getHisMedicineInfoTranslateList() {
        return hisMedicineInfoTranslateList;
    }

    public void setHisMedicineInfoTranslateList(List<HisConsumablesBuyplanDetailsTranslate> hisMedicineInfoTranslateList) {
        this.hisMedicineInfoTranslateList = hisMedicineInfoTranslateList;
    }

    public List<HisConsumablesBuyplanTranslate> getMedicineInfoTranslateList() {
        return medicineInfoTranslateList;
    }

    public void setMedicineInfoTranslateList(List<HisConsumablesBuyplanTranslate> medicineInfoTranslateList) {
        this.medicineInfoTranslateList = medicineInfoTranslateList;
    }

    public List<HisConsumablesDetailsTranslate> getMedicineInfoTranslateArrayList() {
        return medicineInfoTranslateArrayList;
    }

    public void setMedicineInfoTranslateArrayList(List<HisConsumablesDetailsTranslate> medicineInfoTranslateArrayList) {
        this.medicineInfoTranslateArrayList = medicineInfoTranslateArrayList;
    }

    public List<HisConsumablesTranslate> getInfoTranslateList() {
        return infoTranslateList;
    }

    public void setInfoTranslateList(List<HisConsumablesTranslate> infoTranslateList) {
        this.infoTranslateList = infoTranslateList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
