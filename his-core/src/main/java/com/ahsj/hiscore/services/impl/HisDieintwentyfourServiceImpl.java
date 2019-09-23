package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisDieintwentyfourMapper;
import com.ahsj.hiscore.entity.HisDieintwentyfour;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisDieintwentyfourService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
public class HisDieintwentyfourServiceImpl implements HisDieintwentyfourService {

    @Autowired
    HisDieintwentyfourMapper hisDieintwentyfourMapper;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Override
    @Transactional(readOnly = false)
    public Message save(HisDieintwentyfour hisDieintwentyfour) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisDieintwentyfour)){
            hisDieintwentyfourMapper.insert(hisDieintwentyfour);

            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(20);
            hisInpatientMedicalRecord.setTargetId(hisDieintwentyfour.getId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hisDieintwentyfour.getHospitalnumber());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

            return MessageUtil.createMessage(true,"保存成功");
        }else {
            return MessageUtil.createMessage(false,"保存失败，数据为空");

        }
    }
}
