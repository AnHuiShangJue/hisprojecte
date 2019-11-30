package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.IntelligentMapper;
import com.ahsj.wis.entity.Intelligent;
import com.ahsj.wis.service.IntelligentService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.Date;
import java.util.List;

@Service
public class IntelligentServiceImpl implements IntelligentService {

    @Autowired
    IntelligentMapper intelligentMapper;



    @Override
    @Transactional(readOnly = true)
    public List<Intelligent> selectAll() throws Exception {
        return intelligentMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateIntelligent(Intelligent record){
        record.setUpdateDate(new Date());
        if (EmptyUtil.Companion.isNullOrEmpty(record.getId())) {
            record.setUpdateDate(new Date());
            record.setCreateDate(new Date());
            intelligentMapper.insert(record);
            return MessageUtil.createMessage(true,"新增成功！");
        } else {
            Intelligent intelligent = intelligentMapper.selectByPrimaryKey(record.getId());
            intelligent.setId(null);
            intelligent.setCreateDate(new Date());
            intelligent.setUpdateDate(new Date());
            intelligentMapper.insert(intelligent);
            int i = intelligentMapper.updateByPrimaryKeySelective(record);
            if (i > 0) {
                return MessageUtil.createMessage(true, "修改成功！");
            } else {
                return MessageUtil.createMessage(false, "修改失败！");
            }
        }
    }
}
