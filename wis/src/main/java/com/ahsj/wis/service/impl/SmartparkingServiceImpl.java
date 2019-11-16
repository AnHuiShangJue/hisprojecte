package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.SmartparkingMapper;
import com.ahsj.wis.entity.Smartparking;
import com.ahsj.wis.service.SmartparkingService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SmartparkingServiceImpl implements SmartparkingService {

    @Autowired
    SmartparkingMapper smartparkingMapper;



    @Override
    @Transactional(readOnly = true)
    public List<Smartparking> selectAll() throws Exception {
        return smartparkingMapper.selectAll();
    }



    @Override
    @Transactional(readOnly = false)
    public Message updateSmartparking(Smartparking record){
        record.setUpdateDate(new Date());
        Smartparking smartparking = smartparkingMapper.selectByPrimaryKey(record.getId());
        smartparking.setId(null);
        smartparking.setCreateDate(new Date());
        smartparking.setUpdateDate(new Date());
        smartparkingMapper.insert(smartparking);
        int i = smartparkingMapper.updateByPrimaryKeySelective(record);
        if (i>0){
            return MessageUtil.createMessage(true,"修改成功！");
        }else {
            return MessageUtil.createMessage(false,"修改失败！");
        }
    }
}
