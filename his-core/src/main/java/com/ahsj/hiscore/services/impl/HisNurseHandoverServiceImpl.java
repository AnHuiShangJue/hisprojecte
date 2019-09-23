package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisNurseHandoverMapper;
import com.ahsj.hiscore.entity.HisNurseHandover;
import com.ahsj.hiscore.services.HisNurseHandoverService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
public class HisNurseHandoverServiceImpl implements HisNurseHandoverService {
    @Autowired
    HisNurseHandoverMapper hisNurseHandoverMapper;


    @Override
    @Transactional(readOnly = false)
    public Message save(HisNurseHandover hisNurseHandover) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisNurseHandover)){
            return MessageUtil.createMessage(false,"保存失败");
        }else {
            hisNurseHandoverMapper.insert(hisNurseHandover);
            return MessageUtil.createMessage(true,"保存成功");

        }

    }
}
