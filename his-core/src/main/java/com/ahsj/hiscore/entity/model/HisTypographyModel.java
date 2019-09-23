package com.ahsj.hiscore.entity.model;

import com.ahsj.hiscore.common.dto.HisTypographyUserInfoRep;

import java.util.List;

public class HisTypographyModel {

    List<HisTypographyUserInfoRep> hisTypographyUserInfoRep ;

    Long deptId;



    public List<HisTypographyUserInfoRep> getHisTypographyUserInfoRep() {
        return hisTypographyUserInfoRep;
    }

    public void setHisTypographyUserInfoRep(List<HisTypographyUserInfoRep> hisTypographyUserInfoRep) {
        this.hisTypographyUserInfoRep = hisTypographyUserInfoRep;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
