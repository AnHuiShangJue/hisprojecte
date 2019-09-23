package com.ahsj.userinfor.common;

import com.ahsj.userinfor.dao.OrganizationMapper;
import com.ahsj.userinfor.entity.Organization;
import com.ahsj.userinfor.entity.SysCodeDetail;
import com.ahsj.userinfor.services.CodeService;
import com.ahsj.userinfor.services.OrganizationService;
import core.code.Constants;
import core.global.GlobalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description webcontext工具类，用于在系统中注入所需要的全局数据
 * @Params
 * @return
 * @Author chenzhicai
 * @Date 2019-06-13
 * @Time 22:35
 **/
public class WebContextUtil {
    private static Logger logger = LoggerFactory.getLogger(WebContextUtil.class);

    public static ServletContext servletContext = null;

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        WebContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static Object getBean(Class c) {
        return applicationContext.getBean(c);
    }

    public static void setServletCountext(ServletContext p_servletContext) {
        servletContext = p_servletContext;
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(p_servletContext);
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }


    public static void initGlobalData() {
        logger.info("---------------静态全局数据添加-----------------");

        // 字典常量数据添加
        setCodeData();

        // 组织机构数据
        setOrganization();

        // 配置权限敞亮
        setPrivilege();

    }


    @Autowired
    CodeService codeService;

    @Autowired
    OrganizationService organizationService;

    public static void setCodeData() {
        CodeService codeService = (CodeService) WebContextUtil.getBean(CodeService.class);

        try {
            List<SysCodeDetail> lst = codeService.lstSysCodeDetail();
            ConcurrentHashMap<String, List<SysCodeDetail>> codeDataDetail = new ConcurrentHashMap<String, List<SysCodeDetail>>();
            String type = "";
            for (SysCodeDetail sysCodeDetail : lst) {
                if (!type.equals(sysCodeDetail.getType())) {
                    codeDataDetail.put(sysCodeDetail.getType(), new ArrayList<SysCodeDetail>());
                    codeDataDetail.get(sysCodeDetail.getType()).add(sysCodeDetail);
                } else {
                    codeDataDetail.get(sysCodeDetail.getType()).add(sysCodeDetail);
                }

                type = sysCodeDetail.getType();
            }
            GlobalData.codeData.clear();
            GlobalData.codeData.putAll(codeDataDetail);
            WebContextUtil.getServletContext().setAttribute(Constants.GLOBAL_DATA_TABLE, codeDataDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void setOrganization(){
        OrganizationService organizationService = (OrganizationService) WebContextUtil.getBean(OrganizationService.class);
        List<Organization> lst = organizationService.lstAllSysOrganization();
        WebContextUtil.getServletContext().setAttribute(Constants.GLOBAL_DATA_ORANGIATION, lst);
    }

    private static void setPrivilege() {
        WebContextUtil.getServletContext().setAttribute(Constants.GLOBAL_DATA_PRIVILEGE, new PrivilegeConstants());
    }
}
