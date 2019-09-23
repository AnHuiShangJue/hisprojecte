package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.common.SysConstants;
import core.controller.BaseController;
import utils.ReflectUtils;

public class BaseOAController extends BaseController {

    protected String getPriviSql(String tableName) {
        // 管理员账户的时候
        if (SysConstants.ADMIN_COMPANY_ID.equals(getCompanyId())) {
            return "";
        } else {
            return tableName + " like CONCAT(" + getCompanyId() +",'%')";
        }
    }

    protected String getPriviSqlByDept(String tableName) {
        // 管理员账户的时候
        if (SysConstants.ADMIN_COMPANY_ID.equals(getCompanyId())) {
            return "";
        } else {
            return tableName + " like CONCAT(" + getDeptId() +",'%')";
        }
    }

    protected void setCompanyId(Object object) {
        if (!SysConstants.ADMIN_COMPANY_ID.equals(getCompanyId())) {
            ReflectUtils.setFieldValue(object, "companyId", getCompanyId());
        }
    }
//
//    protected void setCasePri(TCaseApplyInfo tCaseApplyInfo) {
//        tCaseApplyInfo.setOrgId(getCompanyId());
//        // 所属机构
//        tCaseApplyInfo.setType(getLoginUserInfo().getType());
//        if (!getCompanyId().equals(getDeptId())) {
//            tCaseApplyInfo.setGetOrgId(getDeptId());
//        }
//        // 不是负责人的时候
//        // 只能看自己的数据
//        if (!Constants.IS_LEADER.equals(getLoginUserInfo().getIsLeader())) {
//            tCaseApplyInfo.setAgentId(getId());
//            tCaseApplyInfo.setUserId(getId());
//        }
//
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isPermitted(PrivilegeConstants.CASE_STATUS_ALL_DATA)) {
//            tCaseApplyInfo.setType("3");
//        }
//    }
}
