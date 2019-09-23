package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisObstetrics extends BaseEntity {
    private Long id;

    private Long hisHospitalManageId;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_married", typeName = "isMarrieds")
    private Integer isMarried;
    private String isMarrieds;

    private String location;

    private String telephone;

    private String job;

    private String education;

    private String cause;

    private String way;

    private String admittingDiagnosis;

    private Double weight;

    private Double bodyTemperature;

    private String pulse;

    private String breathe;

    private String bloodPressure;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_mind", typeName = "isMinds")
    private Integer isMind;
    private String isMinds;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_face", typeName = "isFaces")
    private Integer isFace;
    private String isFaces;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_feeling", typeName = "isFeelings")
    private Integer isFeeling;
    private String isFeelings;

    private String skin;

    private String competence;

    private String self;

    private String previousHistory;

    private String allergicHistory;

    private String other;

    private String gestation;

    private String breast;

    private String lmp;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dueDate;

    private String bleedingDuringPregnancy;

    private String drugPregnancy;

    private String fetalPosition;

    private String fetalHeart;

    private String quickening;

    private String abnormalQuickening;

    private String caul;

    private String colporrhagia;

    private String uterineContraction;

    private String lawContractions;

    private String breastDevelopment;

    private String obstetricsOther;

    private String oedemaCircumstance;

    private String pro;

    private String urineSugar;

    private String educations;

    private String plan;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date tevalDate;

    private Long doctorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHisHospitalManageId() {
        return hisHospitalManageId;
    }

    public void setHisHospitalManageId(Long hisHospitalManageId) {
        this.hisHospitalManageId = hisHospitalManageId;
    }


    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsMarrieds() {
        return isMarrieds;
    }

    public void setIsMarrieds(String isMarrieds) {
        this.isMarrieds = isMarrieds;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way == null ? null : way.trim();
    }

    public String getAdmittingDiagnosis() {
        return admittingDiagnosis;
    }

    public void setAdmittingDiagnosis(String admittingDiagnosis) {
        this.admittingDiagnosis = admittingDiagnosis == null ? null : admittingDiagnosis.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse == null ? null : pulse.trim();
    }

    public String getBreathe() {
        return breathe;
    }

    public void setBreathe(String breathe) {
        this.breathe = breathe == null ? null : breathe.trim();
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
    }

    public Integer getIsMind() {
        return isMind;
    }

    public void setIsMind(Integer isMind) {
        this.isMind = isMind;
    }

    public Integer getIsFace() {
        return isFace;
    }

    public void setIsFace(Integer isFace) {
        this.isFace = isFace;
    }

    public Integer getIsFeeling() {
        return isFeeling;
    }

    public void setIsFeeling(Integer isFeeling) {
        this.isFeeling = isFeeling;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin == null ? null : skin.trim();
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence == null ? null : competence.trim();
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self == null ? null : self.trim();
    }

    public String getPreviousHistory() {
        return previousHistory;
    }

    public void setPreviousHistory(String previousHistory) {
        this.previousHistory = previousHistory == null ? null : previousHistory.trim();
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory == null ? null : allergicHistory.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getGestation() {
        return gestation;
    }

    public void setGestation(String gestation) {
        this.gestation = gestation == null ? null : gestation.trim();
    }

    public String getBreast() {
        return breast;
    }

    public void setBreast(String breast) {
        this.breast = breast == null ? null : breast.trim();
    }

    public String getLmp() {
        return lmp;
    }

    public void setLmp(String lmp) {
        this.lmp = lmp == null ? null : lmp.trim();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getBleedingDuringPregnancy() {
        return bleedingDuringPregnancy;
    }

    public void setBleedingDuringPregnancy(String bleedingDuringPregnancy) {
        this.bleedingDuringPregnancy = bleedingDuringPregnancy == null ? null : bleedingDuringPregnancy.trim();
    }

    public String getDrugPregnancy() {
        return drugPregnancy;
    }

    public void setDrugPregnancy(String drugPregnancy) {
        this.drugPregnancy = drugPregnancy == null ? null : drugPregnancy.trim();
    }

    public String getFetalPosition() {
        return fetalPosition;
    }

    public void setFetalPosition(String fetalPosition) {
        this.fetalPosition = fetalPosition == null ? null : fetalPosition.trim();
    }

    public String getFetalHeart() {
        return fetalHeart;
    }

    public void setFetalHeart(String fetalHeart) {
        this.fetalHeart = fetalHeart == null ? null : fetalHeart.trim();
    }

    public String getQuickening() {
        return quickening;
    }

    public void setQuickening(String quickening) {
        this.quickening = quickening == null ? null : quickening.trim();
    }

    public String getAbnormalQuickening() {
        return abnormalQuickening;
    }

    public void setAbnormalQuickening(String abnormalQuickening) {
        this.abnormalQuickening = abnormalQuickening == null ? null : abnormalQuickening.trim();
    }

    public String getCaul() {
        return caul;
    }

    public void setCaul(String caul) {
        this.caul = caul == null ? null : caul.trim();
    }

    public String getColporrhagia() {
        return colporrhagia;
    }

    public void setColporrhagia(String colporrhagia) {
        this.colporrhagia = colporrhagia == null ? null : colporrhagia.trim();
    }

    public String getUterineContraction() {
        return uterineContraction;
    }

    public void setUterineContraction(String uterineContraction) {
        this.uterineContraction = uterineContraction == null ? null : uterineContraction.trim();
    }

    public String getLawContractions() {
        return lawContractions;
    }

    public void setLawContractions(String lawContractions) {
        this.lawContractions = lawContractions == null ? null : lawContractions.trim();
    }

    public String getBreastDevelopment() {
        return breastDevelopment;
    }

    public void setBreastDevelopment(String breastDevelopment) {
        this.breastDevelopment = breastDevelopment == null ? null : breastDevelopment.trim();
    }

    public String getObstetricsOther() {
        return obstetricsOther;
    }

    public void setObstetricsOther(String obstetricsOther) {
        this.obstetricsOther = obstetricsOther == null ? null : obstetricsOther.trim();
    }

    public String getOedemaCircumstance() {
        return oedemaCircumstance;
    }

    public void setOedemaCircumstance(String oedemaCircumstance) {
        this.oedemaCircumstance = oedemaCircumstance == null ? null : oedemaCircumstance.trim();
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro == null ? null : pro.trim();
    }

    public String getUrineSugar() {
        return urineSugar;
    }

    public void setUrineSugar(String urineSugar) {
        this.urineSugar = urineSugar == null ? null : urineSugar.trim();
    }

    public String getEducations() {
        return educations;
    }

    public void setEducations(String educations) {
        this.educations = educations == null ? null : educations.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public Date getTevalDate() {
        return tevalDate;
    }

    public void setTevalDate(Date tevalDate) {
        this.tevalDate = tevalDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getIsMinds() {
        return isMinds;
    }

    public void setIsMinds(String isMinds) {
        this.isMinds = isMinds;
    }

    public String getIsFaces() {
        return isFaces;
    }

    public void setIsFaces(String isFaces) {
        this.isFaces = isFaces;
    }

    public String getIsFeelings() {
        return isFeelings;
    }

    public void setIsFeelings(String isFeelings) {
        this.isFeelings = isFeelings;
    }
}