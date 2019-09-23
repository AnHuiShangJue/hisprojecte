package com.ahsj.hiscore.services;


import com.ahsj.hiscore.entity.NursingRecordWord;
import core.entity.PageBean;
import core.message.Message;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-21-16-19
 **/
public interface NursingRecordWordService {


    Message saveOrUpdate(NursingRecordWord nursingRecordWord);

    NursingRecordWord selectById(Long id);

    PageBean<NursingRecordWord> list(PageBean<NursingRecordWord> pageBean);
}
