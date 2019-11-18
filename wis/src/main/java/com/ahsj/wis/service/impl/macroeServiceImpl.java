package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.macroeMapper;
import com.ahsj.wis.entity.macroe;
import com.ahsj.wis.service.macroeService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.Date;
import java.util.List;

@Service
public class macroeServiceImpl implements macroeService {

    @Autowired
    macroeMapper macroeMapper;



    @Override
    @Transactional(readOnly = true)
    public List<macroe> selectAll() throws Exception {
        return macroeMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateMacroe(macroe record) {
        record.setUpdateDate(new Date());
        if (EmptyUtil.Companion.isNullOrEmpty(record.getId())) {
            record.setUpdateDate(new Date());
            record.setCreateDate(new Date());
            macroeMapper.insert(record);
            return MessageUtil.createMessage(true, "新增成功！");
        } else {
            macroe macroe = macroeMapper.selectByPrimaryKey(record.getId());
            macroe.setId(null);
            macroe.setCreateDate(new Date());
            macroe.setUpdateDate(new Date());
            macroeMapper.insert(macroe);
            int i = macroeMapper.updateByPrimaryKeySelective(record);
            if (i > 0) {
                return MessageUtil.createMessage(true, "修改成功！");
            } else {
                return MessageUtil.createMessage(false, "修改失败！");
            }
        }
    }
}
