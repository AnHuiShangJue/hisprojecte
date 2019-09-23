package com.ahsj.hiscore.core;

import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.entity.sys.TranslateInfo;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import core.code.Constants;
import core.global.GlobalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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

    public static final String TRANSLATE_ENUM = "TRANSLATE_ENUM";

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


    public static void initGlobalData() throws Exception {
        logger.info("---------------静态全局数据添加-----------------");

        // 字典常量数据添加
        setCodeData();

        // 组织机构数据
        setOrganization();

        // 配置权限敞亮
        setPrivilege();

        /**
         *@功能说明 
         *@Params []
         *@return void
         *@Author XJP
         *@Date 2019/7/24
         *@Time 17:29
        **/
        setTranslate();

    }


    @Autowired
    ICodeService codeService;


    @Autowired
    IOrganizationService iOrganizationService;

    public static void setCodeData() {
        com.ahsj.hiscore.feign.ICodeService codeService = (ICodeService) WebContextUtil.getBean(ICodeService.class);

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
            logger.info("code size is" + codeDataDetail.size());
            WebContextUtil.getServletContext().setAttribute(Constants.GLOBAL_DATA_TABLE, codeDataDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setOrganization() throws Exception {
        IOrganizationService iOrganizationService = (IOrganizationService) WebContextUtil.getBean(IOrganizationService.class);
        List<Organization> lst = iOrganizationService.lstAllSysOrganization();
        logger.info("Organization size is" + lst.size());
        WebContextUtil.getServletContext().setAttribute(Constants.GLOBAL_DATA_ORANGIATION, lst);
    }

    /**
     *@功能说明
     *@Params []
     *@return void
     *@Author XJP
     *@Date 2019/7/24
     *@Time 16:09
    **/
    public static void setTranslate() throws Exception {
        ITranslateService iTranslateService = (ITranslateService) WebContextUtil.getBean(ITranslateService.class);
        List<Translate> list = iTranslateService.queryTranslateAll();
        logger.info("TranslateService size is   " + list.size());
        WebContextUtil.getServletContext().setAttribute(TRANSLATE_ENUM, list);
    }


    /**
     *@功能说明
     *@Params []
     *@return void
     *@Author XJP
     *@Date 2019/7/24
     *@Time 16:09
     **/
    public static void setTranslateInfo() throws Exception {
        ITranslateService iTranslateService = (ITranslateService) WebContextUtil.getBean(ITranslateService.class);
        List<TranslateInfo> translateInfos = iTranslateService.queryTranslateInfoAll();
        logger.info("TranslateInfoService size is   " + translateInfos.size());
        WebContextUtil.getServletContext().setAttribute(TRANSLATE_ENUM, translateInfos);
    }

    private static void setPrivilege() {
//        WebContextUtil.getServletContext().setAttribute(Constants.GLOBAL_DATA_PRIVILEGE, new PrivilegeConstants());
    }
}
