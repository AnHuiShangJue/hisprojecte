package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.TemplateMedicalRecordMapper;
import com.ahsj.hiscore.entity.TemplateMedicalRecord;
import com.ahsj.hiscore.services.TemplateMedialReocSerivce;
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
 * @create: 2019-09-20-12-32
 **/
@Service
public class TemplateMedialReocSerivceImpl implements TemplateMedialReocSerivce {

    @Autowired
    TemplateMedicalRecordMapper templateMedicalRecordMapper;

    /**
     *@Description 新增或更新
     *@Params [userId]
     *@return java.util.List<com.ahsj.hiscore.entity.TemplateMedicalRecord>
     *@Author chenzhicai
     *@Date 2019-09-21
     *@Time 11:18
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(TemplateMedicalRecord templateMedicalRecord) {
        if (EmptyUtil.Companion.isNullOrEmpty(templateMedicalRecord.getId())) {
            templateMedicalRecordMapper.insert(templateMedicalRecord);
            return MessageUtil.createMessage(true, "保存成功！");
        } else {
            templateMedicalRecordMapper.updateByPrimaryKey(templateMedicalRecord);
            return MessageUtil.createMessage(true, "保存成功！");
        }
    }

    /**
     *@Description selectById
     *@Params [userId]
     *@return java.util.List<com.ahsj.hiscore.entity.TemplateMedicalRecord>
     *@Author chenzhicai
     *@Date 2019-09-21
     *@Time 11:18
     **/
    @Override
    @Transactional(readOnly = true)
    public TemplateMedicalRecord selectById(Long id) {
        return templateMedicalRecordMapper.selectByPrimaryKey(id);

    }

    /**
     *@Description 为大病历界面查询模版
     *@Params [userId]
     *@return java.util.List<com.ahsj.hiscore.entity.TemplateMedicalRecord>
     *@Author chenzhicai
     *@Date 2019-09-21
     *@Time 11:18
    **/
    @Override
    @Transactional(readOnly = true)
    public List<TemplateMedicalRecord> listForMedical(Long userId) {
        return templateMedicalRecordMapper.listForMedical(userId);
    }
}

    