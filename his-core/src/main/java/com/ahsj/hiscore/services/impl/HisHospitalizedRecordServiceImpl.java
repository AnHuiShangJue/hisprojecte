package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisHospitalizedRecordMapper;
import com.ahsj.hiscore.entity.HisHospitalizedRecord;
import com.ahsj.hiscore.services.HisHospitalizedRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/18
 * @Time 10:14
 **/
@Service
public class HisHospitalizedRecordServiceImpl implements HisHospitalizedRecordService {

    @Autowired
    HisHospitalizedRecordMapper hisHospitalizedRecordMapper;


    /**
     * @Description 入院登记的新增和修改
     * @Params: [hisHospitalizedRecord]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/18
     * @Time 10:23
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisHospitalizedRecord hisHospitalizedRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalizedRecord.getId())) {
            hisHospitalizedRecordMapper.insert(hisHospitalizedRecord);
            return MessageUtil.createMessage(true, "新增成功！");
        } else {
            hisHospitalizedRecordMapper.updateByPrimaryKeySelective(hisHospitalizedRecord);
            return MessageUtil.createMessage(true, "修改成功！");
        }
    }
}
