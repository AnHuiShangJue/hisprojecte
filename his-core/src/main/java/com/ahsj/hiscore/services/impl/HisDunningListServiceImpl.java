package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisDunningListMapper;
import com.ahsj.hiscore.entity.HisDunningList;
import com.ahsj.hiscore.services.HisDunningListService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;

@Service
public class HisDunningListServiceImpl implements HisDunningListService {

    @Autowired
    HisDunningListMapper hisDunningListMapper;

    @Override
    @Transactional(readOnly = false)
    public Message save(String medicalRecordId, Double money, String number) {
        if (EmptyUtil.Companion.isNullOrEmpty(medicalRecordId)){
            return MessageUtil.createMessage(false,"保存失败");

        }else {
            HisDunningList hisDunningList = new HisDunningList();

            hisDunningList.setMoney(BigDecimal.valueOf(money));
            hisDunningList.setNumber(number);
            hisDunningList.setHospiManagerNumber(medicalRecordId);

            hisDunningListMapper.insert(hisDunningList);
            return MessageUtil.createMessage(true,"保存成功");

        }



    }
}
