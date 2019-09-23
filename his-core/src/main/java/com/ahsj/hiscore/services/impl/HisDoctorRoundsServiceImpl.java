package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisDoctorRoundsMapper;
import com.ahsj.hiscore.entity.HisDoctorRounds;
import com.ahsj.hiscore.services.HisDoctorRoundsService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
public class HisDoctorRoundsServiceImpl implements HisDoctorRoundsService {

    @Autowired
    HisDoctorRoundsMapper hisDoctorRoundsMapper;

    @Override
    @Transactional(readOnly = true)
    public Message saveOrUpdate(HisDoctorRounds hisDoctorRounds) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisDoctorRounds)){
            return MessageUtil.createMessage(false,"保存失败");
        }else {
            hisDoctorRoundsMapper.insert(hisDoctorRounds);
            return MessageUtil.createMessage(true,"保存成功");

        }
    }

    @Override
    @Transactional(readOnly = true)
    public Message sign(HisDoctorRounds hisDoctorRounds) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisDoctorRounds.getDoctorsign())){
            return MessageUtil.createMessage(false,"保存失败");
        }else {
            hisDoctorRoundsMapper.updateByPrimaryKey(hisDoctorRounds);
            return MessageUtil.createMessage(true,"保存成功");

        }
    }
}
