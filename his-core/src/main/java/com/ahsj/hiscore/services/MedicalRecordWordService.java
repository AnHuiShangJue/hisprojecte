package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.MedicalRecordWord;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-20-11-48
 **/
public interface MedicalRecordWordService {

    Message saveOrUpdate(MedicalRecordWord medicalRecordWord);

    MedicalRecordWord selectById(Long id);

    PageBean<MedicalRecordWord> list(PageBean<MedicalRecordWord> pageBean);
}

    