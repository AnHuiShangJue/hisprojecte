package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisNursingAssessment extends BaseEntity {
    private Long id;

    private Long hisHospitalManageId;

    private String nation;

    private Long createUserId;

    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    private String job;

    private String education;

    private String location;

    private String telephone;

    private String cause;

    private String way;

    private String previousHistory;

    private String allergicHistory;

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

    private String vision;

    private String ear;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "competence", typeName = "competences")
    private Integer competence;
    private String competences;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "communication", typeName = "communications")
    private Integer communication;
    private String communications;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "savvy", typeName = "savvys")
    private Integer savvy;
    private String savvys;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "oral_mucosa", typeName = "oralMucosas")
    private Integer oralMucosa;
    private String oralMucosas;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "false_tooth", typeName = "falseTooths")
    private Integer falseTooth;
    private String falseTooths;

    private String skin;

    private String fours;

    private String discharge;

    private String adl;

    private String braden;

    private String morse;

    private String pipeDown;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "contour", typeName = "contours")
    private Integer contour;
    private String contours;

    private String diet;

    private String livingHabit;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "attitude", typeName = "attitudes")
    private Integer attitude;
    private String attitudes;

    private String educations;

    private String plan;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date tevalDate;

    private Long nurseId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_married", typeName = "isMarrieds")
    private Integer isMarried;
    private String isMarrieds;

    private String admittingDiagnosis;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
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

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision == null ? null : vision.trim();
    }

    public String getEar() {
        return ear;
    }

    public void setEar(String ear) {
        this.ear = ear == null ? null : ear.trim();
    }

    public Integer getCompetence() {
        return competence;
    }

    public void setCompetence(Integer competence) {
        this.competence = competence;
    }

    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }

    public Integer getSavvy() {
        return savvy;
    }

    public void setSavvy(Integer savvy) {
        this.savvy = savvy;
    }

    public Integer getOralMucosa() {
        return oralMucosa;
    }

    public void setOralMucosa(Integer oralMucosa) {
        this.oralMucosa = oralMucosa;
    }

    public Integer getFalseTooth() {
        return falseTooth;
    }

    public void setFalseTooth(Integer falseTooth) {
        this.falseTooth = falseTooth;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin == null ? null : skin.trim();
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours == null ? null : fours.trim();
    }

    public String getDischarge() {
        return discharge;
    }

    public void setDischarge(String discharge) {
        this.discharge = discharge == null ? null : discharge.trim();
    }

    public String getAdl() {
        return adl;
    }

    public void setAdl(String adl) {
        this.adl = adl == null ? null : adl.trim();
    }

    public String getBraden() {
        return braden;
    }

    public void setBraden(String braden) {
        this.braden = braden == null ? null : braden.trim();
    }

    public String getMorse() {
        return morse;
    }

    public void setMorse(String morse) {
        this.morse = morse == null ? null : morse.trim();
    }

    public String getPipeDown() {
        return pipeDown;
    }

    public void setPipeDown(String pipeDown) {
        this.pipeDown = pipeDown == null ? null : pipeDown.trim();
    }

    public Integer getContour() {
        return contour;
    }

    public void setContour(Integer contour) {
        this.contour = contour;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet == null ? null : diet.trim();
    }

    public String getLivingHabit() {
        return livingHabit;
    }

    public void setLivingHabit(String livingHabit) {
        this.livingHabit = livingHabit == null ? null : livingHabit.trim();
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
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

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
    }

    public String getAdmittingDiagnosis() {
        return admittingDiagnosis;
    }

    public void setAdmittingDiagnosis(String admittingDiagnosis) {
        this.admittingDiagnosis = admittingDiagnosis == null ? null : admittingDiagnosis.trim();
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

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getCommunications() {
        return communications;
    }

    public void setCommunications(String communications) {
        this.communications = communications;
    }

    public String getSavvys() {
        return savvys;
    }

    public void setSavvys(String savvys) {
        this.savvys = savvys;
    }

    public String getOralMucosas() {
        return oralMucosas;
    }

    public void setOralMucosas(String oralMucosas) {
        this.oralMucosas = oralMucosas;
    }

    public String getFalseTooths() {
        return falseTooths;
    }

    public void setFalseTooths(String falseTooths) {
        this.falseTooths = falseTooths;
    }

    public String getContours() {
        return contours;
    }

    public void setContours(String contours) {
        this.contours = contours;
    }

    public String getAttitudes() {
        return attitudes;
    }

    public void setAttitudes(String attitudes) {
        this.attitudes = attitudes;
    }

    public String getIsMarrieds() {
        return isMarrieds;
    }

    public void setIsMarrieds(String isMarrieds) {
        this.isMarrieds = isMarrieds;
    }
}