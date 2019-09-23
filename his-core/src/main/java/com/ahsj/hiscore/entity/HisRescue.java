package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisRescue  extends BaseEntity {
    private Long id;

    private Long createUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Long updateUserId;

    private Date updateDate;

    private Long patientId;

    private String patientName;

    private Long commandDoctorId;

    private String commandDoctorName;

    private String participants;

    private String participantsname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Date timeOfIllness;

    private String mainSign;

    private String briefSymptomSign;

    private String physicalExaminationResult;

    private String emergencyTestResult;

    private String specialInspectionResult;

    private String vitalSignsChanges;

    private String rescueMeasures;

    private String consultationOpinion;

    private String rescueResult;

    private String terminatingReasons;

    private String nextStep;

    private String medicalCare;

    private Date timeOfDeath;

    private String deathBasis;

    private String familyOpinion;

    private String specialRequirements;

    private String familyName;

    private String relationship;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "yes_or_no", typeName = "isName")//is_enable  disable
    private Integer familyIsSign;//yes_or_no
    private String isName;

    private Date supplementaryTime;

    private Integer peopleIsSign;

    private String recordNumber;

    private String alreadySignId;

    private String alreadySignName;

    private Long hospitalManageId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lowCreateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date highCreateDate;

    public String getIsName() {
        return isName;
    }

    public void setIsName(String isName) {
        this.isName = isName;
    }

    public Date getLowCreateDate() {
        return lowCreateDate;
    }

    public void setLowCreateDate(Date lowCreateDate) {
        this.lowCreateDate = lowCreateDate;
    }

    public Date getHighCreateDate() {
        return highCreateDate;
    }

    public void setHighCreateDate(Date highCreateDate) {
        this.highCreateDate = highCreateDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getCommandDoctorName() {
        return commandDoctorName;
    }

    public void setCommandDoctorName(String commandDoctorName) {
        this.commandDoctorName = commandDoctorName;
    }

    public String getAlreadySignName() {
        return alreadySignName;
    }

    public void setAlreadySignName(String alreadySignName) {
        this.alreadySignName = alreadySignName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getCommandDoctorId() {
        return commandDoctorId;
    }

    public void setCommandDoctorId(Long commandDoctorId) {
        this.commandDoctorId = commandDoctorId;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants == null ? null : participants.trim();
    }

    public String getParticipantsname() {
        return participantsname;
    }

    public void setParticipantsname(String participantsname) {
        this.participantsname = participantsname == null ? null : participantsname.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getTimeOfIllness() {
        return timeOfIllness;
    }

    public void setTimeOfIllness(Date timeOfIllness) {
        this.timeOfIllness = timeOfIllness;
    }

    public String getMainSign() {
        return mainSign;
    }

    public void setMainSign(String mainSign) {
        this.mainSign = mainSign == null ? null : mainSign.trim();
    }

    public String getBriefSymptomSign() {
        return briefSymptomSign;
    }

    public void setBriefSymptomSign(String briefSymptomSign) {
        this.briefSymptomSign = briefSymptomSign == null ? null : briefSymptomSign.trim();
    }

    public String getPhysicalExaminationResult() {
        return physicalExaminationResult;
    }

    public void setPhysicalExaminationResult(String physicalExaminationResult) {
        this.physicalExaminationResult = physicalExaminationResult == null ? null : physicalExaminationResult.trim();
    }

    public String getEmergencyTestResult() {
        return emergencyTestResult;
    }

    public void setEmergencyTestResult(String emergencyTestResult) {
        this.emergencyTestResult = emergencyTestResult == null ? null : emergencyTestResult.trim();
    }

    public String getSpecialInspectionResult() {
        return specialInspectionResult;
    }

    public void setSpecialInspectionResult(String specialInspectionResult) {
        this.specialInspectionResult = specialInspectionResult == null ? null : specialInspectionResult.trim();
    }

    public String getVitalSignsChanges() {
        return vitalSignsChanges;
    }

    public void setVitalSignsChanges(String vitalSignsChanges) {
        this.vitalSignsChanges = vitalSignsChanges == null ? null : vitalSignsChanges.trim();
    }

    public String getRescueMeasures() {
        return rescueMeasures;
    }

    public void setRescueMeasures(String rescueMeasures) {
        this.rescueMeasures = rescueMeasures == null ? null : rescueMeasures.trim();
    }

    public String getConsultationOpinion() {
        return consultationOpinion;
    }

    public void setConsultationOpinion(String consultationOpinion) {
        this.consultationOpinion = consultationOpinion == null ? null : consultationOpinion.trim();
    }

    public String getRescueResult() {
        return rescueResult;
    }

    public void setRescueResult(String rescueResult) {
        this.rescueResult = rescueResult == null ? null : rescueResult.trim();
    }

    public String getTerminatingReasons() {
        return terminatingReasons;
    }

    public void setTerminatingReasons(String terminatingReasons) {
        this.terminatingReasons = terminatingReasons == null ? null : terminatingReasons.trim();
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep == null ? null : nextStep.trim();
    }

    public String getMedicalCare() {
        return medicalCare;
    }

    public void setMedicalCare(String medicalCare) {
        this.medicalCare = medicalCare == null ? null : medicalCare.trim();
    }

    public Date getTimeOfDeath() {
        return timeOfDeath;
    }

    public void setTimeOfDeath(Date timeOfDeath) {
        this.timeOfDeath = timeOfDeath;
    }

    public String getDeathBasis() {
        return deathBasis;
    }

    public void setDeathBasis(String deathBasis) {
        this.deathBasis = deathBasis == null ? null : deathBasis.trim();
    }

    public String getFamilyOpinion() {
        return familyOpinion;
    }

    public void setFamilyOpinion(String familyOpinion) {
        this.familyOpinion = familyOpinion == null ? null : familyOpinion.trim();
    }

    public String getSpecialRequirements() {
        return specialRequirements;
    }

    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements == null ? null : specialRequirements.trim();
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName == null ? null : familyName.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public Integer getFamilyIsSign() {
        return familyIsSign;
    }

    public void setFamilyIsSign(Integer familyIsSign) {
        this.familyIsSign = familyIsSign;
    }

    public Date getSupplementaryTime() {
        return supplementaryTime;
    }

    public void setSupplementaryTime(Date supplementaryTime) {
        this.supplementaryTime = supplementaryTime;
    }

    public Integer getPeopleIsSign() {
        return peopleIsSign;
    }

    public void setPeopleIsSign(Integer peopleIsSign) {
        this.peopleIsSign = peopleIsSign;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getAlreadySignId() {
        return alreadySignId;
    }

    public void setAlreadySignId(String alreadySignId) {
        this.alreadySignId = alreadySignId == null ? null : alreadySignId.trim();
    }

    public Long getHospitalManageId() {
        return hospitalManageId;
    }

    public void setHospitalManageId(Long hospitalManageId) {
        this.hospitalManageId = hospitalManageId;
    }

    @Override
    public String toString() {
        return "HisRescue{" +
                "id=" + id +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", updateUserId=" + updateUserId +
                ", updateDate=" + updateDate +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", commandDoctorId=" + commandDoctorId +
                ", commandDoctorName='" + commandDoctorName + '\'' +
                ", participants='" + participants + '\'' +
                ", participantsname='" + participantsname + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeOfIllness=" + timeOfIllness +
                ", mainSign='" + mainSign + '\'' +
                ", briefSymptomSign='" + briefSymptomSign + '\'' +
                ", physicalExaminationResult='" + physicalExaminationResult + '\'' +
                ", emergencyTestResult='" + emergencyTestResult + '\'' +
                ", specialInspectionResult='" + specialInspectionResult + '\'' +
                ", vitalSignsChanges='" + vitalSignsChanges + '\'' +
                ", rescueMeasures='" + rescueMeasures + '\'' +
                ", consultationOpinion='" + consultationOpinion + '\'' +
                ", rescueResult='" + rescueResult + '\'' +
                ", terminatingReasons='" + terminatingReasons + '\'' +
                ", nextStep='" + nextStep + '\'' +
                ", medicalCare='" + medicalCare + '\'' +
                ", timeOfDeath=" + timeOfDeath +
                ", deathBasis='" + deathBasis + '\'' +
                ", familyOpinion='" + familyOpinion + '\'' +
                ", specialRequirements='" + specialRequirements + '\'' +
                ", familyName='" + familyName + '\'' +
                ", relationship='" + relationship + '\'' +
                ", familyIsSign=" + familyIsSign +
                ", isName='" + isName + '\'' +
                ", supplementaryTime=" + supplementaryTime +
                ", peopleIsSign=" + peopleIsSign +
                ", recordNumber='" + recordNumber + '\'' +
                ", alreadySignId='" + alreadySignId + '\'' +
                ", alreadySignName='" + alreadySignName + '\'' +
                ", hospitalManageId=" + hospitalManageId +
                ", lowCreateDate=" + lowCreateDate +
                ", highCreateDate=" + highCreateDate +
                '}';
    }
}