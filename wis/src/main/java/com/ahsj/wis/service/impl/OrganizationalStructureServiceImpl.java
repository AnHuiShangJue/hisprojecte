package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.OrganizationalStructureMapper;
import com.ahsj.wis.entity.OrganizationalStructure;
import com.ahsj.wis.service.OrganizationalStructureService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.Date;
import java.util.List;

@Service
public class OrganizationalStructureServiceImpl implements OrganizationalStructureService {
    @Autowired
    OrganizationalStructureMapper organizationalStructureMapper;



    @Override
    @Transactional(readOnly = true)
    public List<OrganizationalStructure> selectAll() throws Exception {
        return organizationalStructureMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateOrganization(OrganizationalStructure record){
        record.setUpdateDate(new Date());
        if (EmptyUtil.Companion.isNullOrEmpty(record.getId())) {
            record.setUpdateDate(new Date());
            record.setCreateDate(new Date());
            organizationalStructureMapper.insert(record);
            return MessageUtil.createMessage(true,"新增成功！");
        } else {
            OrganizationalStructure organizationalStructure = organizationalStructureMapper.selectByPrimaryKey(record.getId());
            organizationalStructure.setId(null);
            organizationalStructure.setCreateDate(new Date());
            organizationalStructure.setUpdateDate(new Date());
            organizationalStructureMapper.insert(organizationalStructure);
            int i = organizationalStructureMapper.updateByPrimaryKeySelective(record);
            if (i > 0) {
                return MessageUtil.createMessage(true, "修改成功！");
            } else {
                return MessageUtil.createMessage(false, "修改失败！");
            }
        }
    }
}
