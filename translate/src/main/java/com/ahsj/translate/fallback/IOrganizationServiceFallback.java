package com.ahsj.translate.fallback;


import com.ahsj.translate.entity.Organization;
import com.ahsj.translate.entity.TreeEntity;
import com.ahsj.translate.feign.IOrganizationService;
import core.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public List<TreeEntity> treeCode(Integer lv, String id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new ArrayList<TreeEntity>();
    }

    @Override
    public Organization selectUserByOrgId(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Organization();
    }

//    @Override
//    public ArrayList<UserDept> selectUserByOrgId(String id) {
//        logger.error("服务获取异常,不能够访问组织服务");
//        return new ArrayList<UserDept>();
//    }

    @Override
    public Organization getOrganizationById(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Organization();
    }



    @Override
    public Organization getOrganizationByIds(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Organization();
    }

    @Override
    public PageBean<Organization> queryList(Organization organization) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new PageBean<Organization>();
    }

    @Override
    public List<Organization> queryOrganizationList(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }



}

    