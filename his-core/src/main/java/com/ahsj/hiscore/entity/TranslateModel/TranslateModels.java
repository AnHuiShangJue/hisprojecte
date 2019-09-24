package com.ahsj.hiscore.entity.TranslateModel;

import com.ahsj.hiscore.entity.HisMedicationDetails;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateModels
 * <p>
 * Date:     2019/8/1 20:10
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class TranslateModels implements Serializable {


    private static final long serialVersionUID = 7322926343570307478L;
    OrganizationTranslate organizationTranslate;

    SysCodeDetailTranslate sysCodeDetailTranslate;

    SysCodeTranslate sysCodeTranslate;

    HisProjectTranslate hisProjectTranslate;

    List<OrganizationTranslate> organizationTranslateList;

    List<SysCodeDetailTranslate> sysCodeDetailTranslateList;

    HisMedicineInfoTranslate hisMedicineInfoTranslate;

    HisMedicationDetailsTranslate hisMedicationDetailsTranslate;

    HisAnkleTemplateTranslate hisAnkleTemplateTranslate;

    HisConsumablesBuyplanDetailsTranslate hisConsumablesBuyplanDetailsTranslate;

    HisConsumablesBuyplanTranslate hisConsumablesBuyplanTranslate;

    HisConsumablesDetailsTranslate hisConsumablesDetailsTranslate;

    HisConsumablesTranslate hisConsumablesTranslate;

    List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateList;

    HisPrescriptionTranslate hisPrescriptionTranslate;

    HisMedicalTranslate hisMedicalTranslate;

    List<HisProjectTranslate> hisProjectTranslates;

    List<HisPrescriptionTranslate> hisPrescriptionTranslates;

    List<HisConsumablesTranslate> hisConsumablesTranslates;

    List<HisMedicationDetailsTranslate> hisMedicationDetails;

    public List<HisMedicationDetailsTranslate> getHisMedicationDetails() {
        return hisMedicationDetails;
    }

    public void setHisMedicationDetails(List<HisMedicationDetailsTranslate> hisMedicationDetails) {
        this.hisMedicationDetails = hisMedicationDetails;
    }

    public List<HisConsumablesTranslate> getHisConsumablesTranslates() {
        return hisConsumablesTranslates;
    }

    public void setHisConsumablesTranslates(List<HisConsumablesTranslate> hisConsumablesTranslates) {
        this.hisConsumablesTranslates = hisConsumablesTranslates;
    }

    public List<HisPrescriptionTranslate> getHisPrescriptionTranslates() {
        return hisPrescriptionTranslates;
    }

    public void setHisPrescriptionTranslates(List<HisPrescriptionTranslate> hisPrescriptionTranslates) {
        this.hisPrescriptionTranslates = hisPrescriptionTranslates;
    }

    public List<HisProjectTranslate> getHisProjectTranslates() {
        return hisProjectTranslates;
    }

    public void setHisProjectTranslates(List<HisProjectTranslate> hisProjectTranslates) {
        this.hisProjectTranslates = hisProjectTranslates;
    }

    List<HisMedicineInfoTranslate> hisMedicineInfoTranslates;

    public List<HisMedicineInfoTranslate> getHisMedicineInfoTranslates() {
        return hisMedicineInfoTranslates;
    }

    public void setHisMedicineInfoTranslates(List<HisMedicineInfoTranslate> hisMedicineInfoTranslates) {
        this.hisMedicineInfoTranslates = hisMedicineInfoTranslates;
    }

    Long userId;

    public TranslateModels() {
    }


    public HisMedicalTranslate getHisMedicalTranslate() {
        return hisMedicalTranslate;
    }

    public void setHisMedicalTranslate(HisMedicalTranslate hisMedicalTranslate) {
        this.hisMedicalTranslate = hisMedicalTranslate;
    }

    public HisProjectTranslate getHisProjectTranslate() {
        return hisProjectTranslate;
    }

    public void setHisProjectTranslate(HisProjectTranslate hisProjectTranslate) {
        this.hisProjectTranslate = hisProjectTranslate;
    }

    public List<OrganizationTranslate> getOrganizationTranslateList() {
        return organizationTranslateList;
    }

    public void setOrganizationTranslateList(List<OrganizationTranslate> organizationTranslateList) {
        this.organizationTranslateList = organizationTranslateList;
    }

    public List<SysCodeDetailTranslate> getSysCodeDetailTranslateList() {
        return sysCodeDetailTranslateList;
    }

    public void setSysCodeDetailTranslateList(List<SysCodeDetailTranslate> sysCodeDetailTranslateList) {
        this.sysCodeDetailTranslateList = sysCodeDetailTranslateList;
    }

    public HisMedicineInfoTranslate getHisMedicineInfoTranslate() {
        return hisMedicineInfoTranslate;
    }

    public void setHisMedicineInfoTranslate(HisMedicineInfoTranslate hisMedicineInfoTranslate) {
        this.hisMedicineInfoTranslate = hisMedicineInfoTranslate;
    }

    public HisMedicationDetailsTranslate getHisMedicationDetailsTranslate() {
        return hisMedicationDetailsTranslate;
    }

    public void setHisMedicationDetailsTranslate(HisMedicationDetailsTranslate hisMedicationDetailsTranslate) {
        this.hisMedicationDetailsTranslate = hisMedicationDetailsTranslate;
    }

    public HisAnkleTemplateTranslate getHisAnkleTemplateTranslate() {
        return hisAnkleTemplateTranslate;
    }

    public void setHisAnkleTemplateTranslate(HisAnkleTemplateTranslate hisAnkleTemplateTranslate) {
        this.hisAnkleTemplateTranslate = hisAnkleTemplateTranslate;
    }

    public HisConsumablesBuyplanDetailsTranslate getHisConsumablesBuyplanDetailsTranslate() {
        return hisConsumablesBuyplanDetailsTranslate;
    }

    public void setHisConsumablesBuyplanDetailsTranslate(HisConsumablesBuyplanDetailsTranslate hisConsumablesBuyplanDetailsTranslate) {
        this.hisConsumablesBuyplanDetailsTranslate = hisConsumablesBuyplanDetailsTranslate;
    }

    public HisConsumablesBuyplanTranslate getHisConsumablesBuyplanTranslate() {
        return hisConsumablesBuyplanTranslate;
    }

    public void setHisConsumablesBuyplanTranslate(HisConsumablesBuyplanTranslate hisConsumablesBuyplanTranslate) {
        this.hisConsumablesBuyplanTranslate = hisConsumablesBuyplanTranslate;
    }

    public HisConsumablesDetailsTranslate getHisConsumablesDetailsTranslate() {
        return hisConsumablesDetailsTranslate;
    }

    public void setHisConsumablesDetailsTranslate(HisConsumablesDetailsTranslate hisConsumablesDetailsTranslate) {
        this.hisConsumablesDetailsTranslate = hisConsumablesDetailsTranslate;
    }

    public HisConsumablesTranslate getHisConsumablesTranslate() {
        return hisConsumablesTranslate;
    }

    public void setHisConsumablesTranslate(HisConsumablesTranslate hisConsumablesTranslate) {
        this.hisConsumablesTranslate = hisConsumablesTranslate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrganizationTranslate getOrganizationTranslate() {
        return organizationTranslate;
    }

    public void setOrganizationTranslate(OrganizationTranslate organizationTranslate) {
        this.organizationTranslate = organizationTranslate;
    }

    public SysCodeDetailTranslate getSysCodeDetailTranslate() {
        return sysCodeDetailTranslate;
    }

    public void setSysCodeDetailTranslate(SysCodeDetailTranslate sysCodeDetailTranslate) {
        this.sysCodeDetailTranslate = sysCodeDetailTranslate;
    }

    public SysCodeTranslate getSysCodeTranslate() {
        return sysCodeTranslate;
    }

    public void setSysCodeTranslate(SysCodeTranslate sysCodeTranslate) {
        this.sysCodeTranslate = sysCodeTranslate;
    }

    public List<HisPrescriptionMedicineTranslate> getHisPrescriptionMedicineTranslateList() {
        return hisPrescriptionMedicineTranslateList;
    }

    public void setHisPrescriptionMedicineTranslateList(List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateList) {
        this.hisPrescriptionMedicineTranslateList = hisPrescriptionMedicineTranslateList;
    }

    public HisPrescriptionTranslate getHisPrescriptionTranslate() {
        return hisPrescriptionTranslate;
    }

    public void setHisPrescriptionTranslate(HisPrescriptionTranslate hisPrescriptionTranslate) {
        this.hisPrescriptionTranslate = hisPrescriptionTranslate;
    }
}
