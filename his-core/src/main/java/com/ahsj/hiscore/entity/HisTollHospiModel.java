package com.ahsj.hiscore.entity;

import java.util.List;

public class HisTollHospiModel {

    public HisTollHospiModel() {
    }

    HisTollRecord hisTollRecord;
    List<HisTollDetails> hisTollDetailsList;
    Integer isNurse;

    List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList;

    public List<HisApplicationForDrugReturnDetails> getHisApplicationForDrugReturnDetailsList() {
        return hisApplicationForDrugReturnDetailsList;
    }

    public void setHisApplicationForDrugReturnDetailsList(List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList) {
        this.hisApplicationForDrugReturnDetailsList = hisApplicationForDrugReturnDetailsList;
    }

    public HisTollRecord getHisTollRecord() {
        return hisTollRecord;
    }

    public void setHisTollRecord(HisTollRecord hisTollRecord) {
        this.hisTollRecord = hisTollRecord;
    }

    public List<HisTollDetails> getHisTollDetailsList() {
        return hisTollDetailsList;
    }

    public void setHisTollDetailsList(List<HisTollDetails> hisTollDetailsList) {
        this.hisTollDetailsList = hisTollDetailsList;
    }

    public Integer getIsNurse() {
        return isNurse;
    }

    public void setIsNurse(Integer isNurse) {
        this.isNurse = isNurse;
    }
}
