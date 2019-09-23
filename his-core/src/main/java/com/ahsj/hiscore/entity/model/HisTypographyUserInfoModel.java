package com.ahsj.hiscore.entity.model;

import com.ahsj.hiscore.entity.HisTypographyUserInfo;
import core.entity.BaseEntity;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisTypographyUserInfoModel
 * <p>
 * Date:     2019/7/21 10:15
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

public class HisTypographyUserInfoModel extends BaseEntity {

    List<HisTypographyUserInfo> typographyUserInfoList;

    Long deptId;

    public HisTypographyUserInfoModel() {
    }

    public List<HisTypographyUserInfo> getTypographyUserInfoList() {
        return typographyUserInfoList;
    }

    public void setTypographyUserInfoList(List<HisTypographyUserInfo> typographyUserInfoList) {
        this.typographyUserInfoList = typographyUserInfoList;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
