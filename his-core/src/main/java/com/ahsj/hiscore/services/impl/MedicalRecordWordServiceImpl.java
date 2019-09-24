package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.MedicalRecordWordMapper;
import com.ahsj.hiscore.entity.MedicalRecordWord;
import com.ahsj.hiscore.services.MedicalRecordWordService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-20-12-23
 **/
@Service
public class MedicalRecordWordServiceImpl implements MedicalRecordWordService {

    @Autowired
    MedicalRecordWordMapper medicalRecordWordMapper;

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(MedicalRecordWord medicalRecordWord, Long type) {
            //1说明是从模版过来，那么一定是保存
            if(type.intValue() == 1){
                medicalRecordWordMapper.insert(medicalRecordWord);
            }else if(type.intValue() == 2){
                medicalRecordWordMapper.updateByPrimaryKey(medicalRecordWord);
            }
            return MessageUtil.createMessage(true,"保存成功！");

    }

    @Override
    @Transactional(readOnly = true)
    public MedicalRecordWord selectById(Long id) {
        return medicalRecordWordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<MedicalRecordWord> list(PageBean<MedicalRecordWord> pageBean) {
        pageBean.setData(medicalRecordWordMapper.list(pageBean));
        return pageBean;
    }
}

    