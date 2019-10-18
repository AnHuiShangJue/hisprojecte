package com.ahsj.wxapplet.services.impl;


import com.ahsj.wxapplet.dao.OrganizationMapper;
import com.ahsj.wxapplet.entity.Organization;
import com.ahsj.wxapplet.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import utils.EmptyUtil;

public class BaseServiceImpl implements BaseService {

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public Organization companyInfo(String deptCode) {
        String treeId = null;
        if (!EmptyUtil.Companion.isNullOrEmpty(deptCode) && deptCode.length() >= 4) {
            treeId = deptCode.substring(0, 4);
        } else {
            treeId = deptCode;
        }
        return (Organization) organizationMapper.selectByTreeId(treeId);
    }

}
