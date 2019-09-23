package com.ahsj.hiscore.controller;

import com.ahsj.hiscore.common.SysConstants;
import core.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import utils.ReflectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseOAController extends BaseController {


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().print("exception");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected String getPriviSql(String tableName) {
        // 管理员账户的时候
        if (SysConstants.ADMIN_COMPANY_ID.equals(getCompanyId())) {
            return "";
        } else {
            return tableName + " like CONCAT(" + getCompanyId() + ",'%')";
        }
    }

    protected String getPriviSqlByDept(String tableName) {
        // 管理员账户的时候
        if (SysConstants.ADMIN_COMPANY_ID.equals(getCompanyId())) {
            return "";
        } else {
            return tableName + " like CONCAT(" + getDeptId() + ",'%')";
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
    String getProjectsPath() {
        return System.getProperty("user.dir");
    }

    String getJavaPath() {
        return System.getProperty("user.dir") + "/src/main/java";
    }

    String getResourecsPath() {
        return System.getProperty("user.dir") + "/src/main/resources";
    }

    String getStaticPath() {
        return System.getProperty("user.dir") + "/src/main/resources/static";
    }

    String getTemplatesPath() {
        return System.getProperty("user.dir") + "/src/main/resources/templates";
    }

    String getJarStaticPath() {
        return System.getProperty("user.dir") + "/resources/main/static";
    }

    String getJarUploadfilesPath() {
        return System.getProperty("user.dir") + "/resources/main/uploadfiles";
    }

    public String getJarResourcesPath() {
        return System.getProperty("user.dir") + "/resources/main";
    }

    /**
     * @return java.lang.String
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/9/6
     * @Time 10:05
     **/
    public String getJarResourcesPaths() {
        return System.getProperty("user.dir") + "/his-core/src/main/resources";
    }
}
