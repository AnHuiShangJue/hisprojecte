package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.MedicalRecordWord;
import com.ahsj.hiscore.entity.TemplateMedicalRecord;
import core.message.Message;

import java.util.List;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-20-12-22
 **/
public interface TemplateMedialReocSerivce {

    Message saveOrUpdate(TemplateMedicalRecord templateMedicalRecord);

    TemplateMedicalRecord selectById(Long id);

    List<TemplateMedicalRecord> listForMedical(Long userId);
}
