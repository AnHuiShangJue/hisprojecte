package com.ahsj.smartparkcore.fallback;

import com.ahsj.smartparkcore.entity.sys.Organization;
import com.ahsj.smartparkcore.feign.IOrganizationService;
import core.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-01-17-54
 **/

@RestController
public class IOrganizationServiceFallback implements IOrganizationService {

    Logger logger = LoggerFactory.getLogger(IOrganizationServiceFallback.class.getSimpleName());

    @Override
    public List<Organization> lstAllSysOrganization() {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<Organization>();
    }


    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:55
     **/
    @Override
    public Organization selectUserByOrgId(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Organization();
    }



    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:55
     **/
    @Override
    public Organization getOrganizationById(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Organization();
    }


    /**
     * @return com.ahsj.hiscore.entity.sys.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:55
     **/
    @Override
    public Organization getOrganizationByIds(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Organization();
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:55
     **/
    @Override
    public PageBean<Organization> queryList(@RequestBody Organization organization) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new PageBean<Organization>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:55
     **/
    @Override
    public List<Organization> queryOrganizationList(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:55
     **/
    @Override
    public List<Organization> queryTranslateInfoByDate(Organization organization) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.Organization>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 15:08
     **/
    @Override
    public List<Organization> getOrganizationAll() throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }


}

    