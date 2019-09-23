package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisDailyCourse  extends BaseEntity {
    private Long id;

    private Long hisHospitalManageId;

    private String symptom;

    private Long createUserId;

    private Date createDate;

    private Date updateDate;

    private Long updateUserId;

    private String emotion;

    private String psychology;

    private String diet;

    private String sleep;

    private String relieveOneself;

    private String lesionChange;

    private String symptomChange;

    private String signChange;

    private String diagnose;

    private String examine;

    private String clinicImportant;

    private String clinic;

    private String measure;

    private String measureResult;

    private String evolve;

    private String detail;

    private String indication;

    private String kind;

    private String dosage;

    private String prescriptionUpdate;

    private Long prescriptionUpdateId;

    private String updateReason;

    private String circulation;

    private String circulationIndication;

    private String circulationKind;

    private String circulationAmount;

    private String bloodTransfusionReflect;

    private String wardRound;

    private String consultation;

    private String executiveCondition;

    private String importantThing;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date recordDate;

    private Long doctorId;

    private String sign;

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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion == null ? null : emotion.trim();
    }

    public String getPsychology() {
        return psychology;
    }

    public void setPsychology(String psychology) {
        this.psychology = psychology == null ? null : psychology.trim();
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet == null ? null : diet.trim();
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep == null ? null : sleep.trim();
    }

    public String getRelieveOneself() {
        return relieveOneself;
    }

    public void setRelieveOneself(String relieveOneself) {
        this.relieveOneself = relieveOneself == null ? null : relieveOneself.trim();
    }

    public String getLesionChange() {
        return lesionChange;
    }

    public void setLesionChange(String lesionChange) {
        this.lesionChange = lesionChange == null ? null : lesionChange.trim();
    }

    public String getSymptomChange() {
        return symptomChange;
    }

    public void setSymptomChange(String symptomChange) {
        this.symptomChange = symptomChange == null ? null : symptomChange.trim();
    }

    public String getSignChange() {
        return signChange;
    }

    public void setSignChange(String signChange) {
        this.signChange = signChange == null ? null : signChange.trim();
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose == null ? null : diagnose.trim();
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine == null ? null : examine.trim();
    }

    public String getClinicImportant() {
        return clinicImportant;
    }

    public void setClinicImportant(String clinicImportant) {
        this.clinicImportant = clinicImportant == null ? null : clinicImportant.trim();
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic == null ? null : clinic.trim();
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }

    public String getMeasureResult() {
        return measureResult;
    }

    public void setMeasureResult(String measureResult) {
        this.measureResult = measureResult == null ? null : measureResult.trim();
    }

    public String getEvolve() {
        return evolve;
    }

    public void setEvolve(String evolve) {
        this.evolve = evolve == null ? null : evolve.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication == null ? null : indication.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }

    public String getPrescriptionUpdate() {
        return prescriptionUpdate;
    }

    public void setPrescriptionUpdate(String prescriptionUpdate) {
        this.prescriptionUpdate = prescriptionUpdate == null ? null : prescriptionUpdate.trim();
    }

    public Long getPrescriptionUpdateId() {
        return prescriptionUpdateId;
    }

    public void setPrescriptionUpdateId(Long prescriptionUpdateId) {
        this.prescriptionUpdateId = prescriptionUpdateId;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason == null ? null : updateReason.trim();
    }

    public String getCirculation() {
        return circulation;
    }

    public void setCirculation(String circulation) {
        this.circulation = circulation == null ? null : circulation.trim();
    }

    public String getCirculationIndication() {
        return circulationIndication;
    }

    public void setCirculationIndication(String circulationIndication) {
        this.circulationIndication = circulationIndication == null ? null : circulationIndication.trim();
    }

    public String getCirculationKind() {
        return circulationKind;
    }

    public void setCirculationKind(String circulationKind) {
        this.circulationKind = circulationKind == null ? null : circulationKind.trim();
    }

    public String getCirculationAmount() {
        return circulationAmount;
    }

    public void setCirculationAmount(String circulationAmount) {
        this.circulationAmount = circulationAmount == null ? null : circulationAmount.trim();
    }

    public String getBloodTransfusionReflect() {
        return bloodTransfusionReflect;
    }

    public void setBloodTransfusionReflect(String bloodTransfusionReflect) {
        this.bloodTransfusionReflect = bloodTransfusionReflect == null ? null : bloodTransfusionReflect.trim();
    }

    public String getWardRound() {
        return wardRound;
    }

    public void setWardRound(String wardRound) {
        this.wardRound = wardRound == null ? null : wardRound.trim();
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation == null ? null : consultation.trim();
    }

    public String getExecutiveCondition() {
        return executiveCondition;
    }

    public void setExecutiveCondition(String executiveCondition) {
        this.executiveCondition = executiveCondition == null ? null : executiveCondition.trim();
    }

    public String getImportantThing() {
        return importantThing;
    }

    public void setImportantThing(String importantThing) {
        this.importantThing = importantThing == null ? null : importantThing.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }
}