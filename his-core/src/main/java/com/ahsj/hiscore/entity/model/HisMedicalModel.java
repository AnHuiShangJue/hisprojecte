package com.ahsj.hiscore.entity.model;

import com.ahsj.hiscore.entity.*;

import java.util.List;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-11-09-06
 **/

public class HisMedicalModel {

    public HisMedicalModel() {
    }


    Long recordId;
    HisMedical mediCard;


    List<HisMedicationDetails> mediDetail;

    List<HisRecordProject> projects;

    List<HisInfusion> infusionDetail;

    public HisMedical getMediCard() {
        return mediCard;
    }

    public void setMediCard(HisMedical mediCard) {
        this.mediCard = mediCard;
    }

    public List<HisMedicationDetails> getMediDetail() {
        return mediDetail;
    }

    public void setMediDetail(List<HisMedicationDetails> mediDetail) {
        this.mediDetail = mediDetail;
    }


    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public List<HisRecordProject> getProjects() {
        return projects;
    }

    public void setProjects(List<HisRecordProject> projects) {
        this.projects = projects;
    }

    public List<HisInfusion> getInfusionDetail() {
        return infusionDetail;
    }

    public void setInfusionDetail(List<HisInfusion> infusionDetail) {
        this.infusionDetail = infusionDetail;
    }
}

    