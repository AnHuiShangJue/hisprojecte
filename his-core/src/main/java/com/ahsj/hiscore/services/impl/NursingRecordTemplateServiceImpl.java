package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.NursingRecordTemplateMapper;
import com.ahsj.hiscore.entity.NursingRecordTemplate;
import com.ahsj.hiscore.services.NursingRecordTemplateService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-21-16-21
 **/
@Service
public class NursingRecordTemplateServiceImpl implements NursingRecordTemplateService {

    @Autowired
    NursingRecordTemplateMapper nursingRecordTemplateMapper;


    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(NursingRecordTemplate nursingRecordTemplate) {
        if (EmptyUtil.Companion.isNullOrEmpty(nursingRecordTemplate.getId())) {
            nursingRecordTemplateMapper.insert(nursingRecordTemplate);
            return MessageUtil.createMessage(true, "保存成功！");
        } else {
            nursingRecordTemplateMapper.updateByPrimaryKey(nursingRecordTemplate);
            return MessageUtil.createMessage(true, "保存成功！");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public NursingRecordTemplate selectById(Long id) {
        return nursingRecordTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NursingRecordTemplate> listForMedical(Long userId) {
        return nursingRecordTemplateMapper.listForMedical(userId);

    }
}

    