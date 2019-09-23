package com.ahsj.translate.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class HisMedicineInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2082031171868675700L;
    private Long id;

    private String drugsNumb;

    private String drugsName;

    private String drugsAlias;

    private String drugsSpec;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "prescription",typeName = "precriptionName")
    private Integer prescription;
    private String precriptionName;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "mental_medicine",typeName = "mentalmedicine")
    private Integer mentalMedicine;
    private String mentalmedicine;

    private String largeUnit;

    private String smallUnit;

    private Short conversionRate;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "disable",typeName = "disabled")
    private Integer disable;
    private String disabled;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "level",typeName = "leveld")
    private Integer level;
    private String leveld;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "medical_insurance_sign",typeName = "medicalinsurancesign")
    private Integer medicalInsuranceSign;
    private String medicalinsurancesign;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "placeOrigin",typeName = "placeoriginName")
    private String placeorigin;
    private String placeoriginName;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "base_medicine",typeName = "basemedicine")
    private Integer baseMedicine;
    private String basemedicine;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "narcotic_drugs",typeName = "narcoticdrugs")
    private Integer narcoticDrugs;
    private String narcoticdrugs;

    private String remarks;

    private String createUserId;

    private Date createDate;

    private String updateUserId;

    private Date updateDate;

    private Double enterPrice;

    public Double getEnterPrice() {
        return enterPrice;
    }

    public void setEnterPrice(Double enterPrice) {
        this.enterPrice = enterPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb == null ? null : drugsNumb.trim();
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName == null ? null : drugsName.trim();
    }

    public String getDrugsAlias() {
        return drugsAlias;
    }

    public void setDrugsAlias(String drugsAlias) {
        this.drugsAlias = drugsAlias == null ? null : drugsAlias.trim();
    }

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec == null ? null : drugsSpec.trim();
    }

    public Integer getPrescription() {
        return prescription;
    }

    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    public Integer getMentalMedicine() {
        return mentalMedicine;
    }

    public void setMentalMedicine(Integer mentalMedicine) {
        this.mentalMedicine = mentalMedicine;
    }

    public String getLargeUnit() {
        return largeUnit;
    }

    public void setLargeUnit(String largeUnit) {
        this.largeUnit = largeUnit == null ? null : largeUnit.trim();
    }

    public String getSmallUnit() {
        return smallUnit;
    }

    public void setSmallUnit(String smallUnit) {
        this.smallUnit = smallUnit == null ? null : smallUnit.trim();
    }

    public Short getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Short conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMedicalInsuranceSign() {
        return medicalInsuranceSign;
    }

    public void setMedicalInsuranceSign(Integer medicalInsuranceSign) {
        this.medicalInsuranceSign = medicalInsuranceSign;
    }

    public String getPlaceorigin() {
        return placeorigin;
    }

    public void setPlaceorigin(String placeorigin) {
        this.placeorigin = placeorigin == null ? null : placeorigin.trim();
    }

    public Integer getBaseMedicine() {
        return baseMedicine;
    }

    public void setBaseMedicine(Integer baseMedicine) {
        this.baseMedicine = baseMedicine;
    }

    public Integer getNarcoticDrugs() {
        return narcoticDrugs;
    }

    public void setNarcoticDrugs(Integer narcoticDrugs) {
        this.narcoticDrugs = narcoticDrugs;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    @Override
    public String getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Override
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPrecriptionName() {
        return precriptionName;
    }

    public void setPrecriptionName(String precriptionName) {
        this.precriptionName = precriptionName;
    }

    public String getMentalmedicine() {
        return mentalmedicine;
    }

    public void setMentalmedicine(String mentalmedicine) {
        this.mentalmedicine = mentalmedicine;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getLeveld() {
        return leveld;
    }

    public void setLeveld(String leveld) {
        this.leveld = leveld;
    }

    public String getMedicalinsurancesign() {
        return medicalinsurancesign;
    }

    public void setMedicalinsurancesign(String medicalinsurancesign) {
        this.medicalinsurancesign = medicalinsurancesign;
    }

    public String getBasemedicine() {
        return basemedicine;
    }

    public void setBasemedicine(String basemedicine) {
        this.basemedicine = basemedicine;
    }

    public String getNarcoticdrugs() {
        return narcoticdrugs;
    }

    public void setNarcoticdrugs(String narcoticdrugs) {
        this.narcoticdrugs = narcoticdrugs;
    }

    public String getPlaceoriginName() {
        return placeoriginName;
    }

    public void setPlaceoriginName(String placeoriginName) {
        this.placeoriginName = placeoriginName;
    }
}