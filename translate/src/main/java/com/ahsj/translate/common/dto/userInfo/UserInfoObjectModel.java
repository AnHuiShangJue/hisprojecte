package com.ahsj.translate.common.dto.userInfo;

import com.ahsj.translate.common.dto.OrganizationModel;
import com.ahsj.translate.entity.Organization;
import com.ahsj.translate.entity.SysCode;
import com.ahsj.translate.entity.SysCodeDetail;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: UserInfoObjectModel
 * <p>
 * Date:     2019/7/31 14:08
 *
 * @author XJP
 * @create 2019/7/31
 * @since 1.0.0
 */

public class UserInfoObjectModel implements Serializable {

    Long userId;

    Organization organization;

    SysCodeDetail sysCodeDetail;

    OrganizationModel organizationModel;

    SysCode sysCode;

    public SysCode getSysCode() {
        return sysCode;
    }

    public void setSysCode(SysCode sysCode) {
        this.sysCode = sysCode;
    }

    public UserInfoObjectModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public SysCodeDetail getSysCodeDetail() {
        return sysCodeDetail;
    }

    public void setSysCodeDetail(SysCodeDetail sysCodeDetail) {
        this.sysCodeDetail = sysCodeDetail;
    }

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
    }
}
