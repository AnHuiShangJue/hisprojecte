package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.NursingRecordTemplateMapper;
import com.ahsj.hiscore.dao.NursingRecordWordMapper;
import com.ahsj.hiscore.entity.NursingRecordWord;
import com.ahsj.hiscore.services.NursingRecordWordService;
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
 * @create: 2019-09-21-16-20
 **/
@Service
public class NursingRecordWordServiceImpl implements NursingRecordWordService {

    @Autowired
    NursingRecordWordMapper nursingRecordWordMapper;

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(NursingRecordWord nursingRecordWord) {
        if(EmptyUtil.Companion.isNullOrEmpty(nursingRecordWord.getId())){
            nursingRecordWordMapper.insert(nursingRecordWord);
            return MessageUtil.createMessage(true,"保存成功！");
        }else{
            nursingRecordWordMapper.updateByPrimaryKey(nursingRecordWord);
            return MessageUtil.createMessage(true,"保存成功！");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public NursingRecordWord selectById(Long id) {
        return nursingRecordWordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<NursingRecordWord> list(PageBean<NursingRecordWord> pageBean) {
        pageBean.setData(nursingRecordWordMapper.list(pageBean));
        return pageBean;
    }
}

    