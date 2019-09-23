package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.NursingRecordTemplate;
import core.message.Message;

import java.util.List;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-21-16-18
 **/
public interface NursingRecordTemplateService {

    Message saveOrUpdate(NursingRecordTemplate nursingRecordTemplate);

    NursingRecordTemplate selectById(Long id);

    List<NursingRecordTemplate> listForMedical(Long userId);
}
