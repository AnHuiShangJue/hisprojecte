package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.SmartparkMapper;
import com.ahsj.wis.entity.Smartpark;
import com.ahsj.wis.service.SmartparkService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.Date;
import java.util.List;

@Service
public class SmartparkServiceImpl implements SmartparkService {

    @Autowired
    SmartparkMapper smartparkMapper;



    @Override
    @Transactional(readOnly = true)
    public List<Smartpark> selectAll() throws Exception {
        return smartparkMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateSmartpark(Smartpark record) {
        record.setUpdateDate(new Date());
        if (EmptyUtil.Companion.isNullOrEmpty(record.getId())) {
            record.setUpdateDate(new Date());
            record.setCreateDate(new Date());
            smartparkMapper.insert(record);
            return MessageUtil.createMessage(true,"新增成功！");
        } else {
            Smartpark smartpark = smartparkMapper.selectByPrimaryKey(record.getId());
            smartpark.setId(null);
            smartpark.setCreateDate(new Date());
            smartpark.setUpdateDate(new Date());
            smartparkMapper.insert(smartpark);
            int i = smartparkMapper.updateByPrimaryKeySelective(record);
            if (i > 0) {
                return MessageUtil.createMessage(true, "修改成功！");
            } else {
                return MessageUtil.createMessage(false, "修改失败！");
            }
        }
    }
}
