package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisNurseHandoverPatientInfoMapper;
import com.ahsj.hiscore.entity.HisNurseHandoverPatientInfo;
import com.ahsj.hiscore.services.HisNurseHandoverPatientInfoService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;
import java.util.List;

@Service
public class HisNurseHandoverPatientInfoServiceImpl implements HisNurseHandoverPatientInfoService {

    @Autowired
    HisNurseHandoverPatientInfoMapper hisNurseHandoverPatientInfoMapper;


    @Override
    @Transactional(readOnly = false)
    public Message save(List<HisNurseHandoverPatientInfo> hisNurseHandoverPatientInfoList) throws Exception {

        if (hisNurseHandoverPatientInfoList.size() <= 0){
            return MessageUtil.createMessage(false,"保存失败");
        }else {
            hisNurseHandoverPatientInfoMapper.insertBatch(hisNurseHandoverPatientInfoList);
            return MessageUtil.createMessage(true,"保存成功");

        }
    }
}
