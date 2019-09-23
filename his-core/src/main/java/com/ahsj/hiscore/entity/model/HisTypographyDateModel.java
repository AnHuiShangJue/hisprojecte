package com.ahsj.hiscore.entity.model;

import com.ahsj.hiscore.entity.HisTypographyUserInfo;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisTypographyDateModel
 * <p>
 * Date:     2019/7/19 16:05
 *
 * @author XJP
 * @create 2019/7/19
 * @since 1.0.0
 */

public class HisTypographyDateModel {

    List<HisTypographyUserInfo> typographyUserInfoList;

    String[] typographyUserInfoListdate;

    Long deptId;

    public String[] getTypographyUserInfoListdate() {
        return typographyUserInfoListdate;
    }

    public void setTypographyUserInfoListdate(String[] typographyUserInfoListdate) {
        this.typographyUserInfoListdate = typographyUserInfoListdate;
    }

    public List<HisTypographyUserInfo> getTypographyUserInfoList() {
        return typographyUserInfoList;
    }

    public void setTypographyUserInfoList(List<HisTypographyUserInfo> typographyUserInfoList) {
        this.typographyUserInfoList = typographyUserInfoList;
    }


    public HisTypographyDateModel() {
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
