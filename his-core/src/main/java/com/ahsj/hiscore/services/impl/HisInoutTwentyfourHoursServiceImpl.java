package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisInoutTwentyfourHoursMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInoutTwentyfourHours;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInoutTwentyfourHoursService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
public class HisInoutTwentyfourHoursServiceImpl implements HisInoutTwentyfourHoursService {

    @Autowired
    HisInoutTwentyfourHoursMapper hisInoutTwentyfourHoursMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;


    @Override
    @Transactional(readOnly = false)
    public Message save(HisInoutTwentyfourHours hisInoutTwentyfourHours) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisInoutTwentyfourHours)){
            hisInoutTwentyfourHoursMapper.insert(hisInoutTwentyfourHours);

            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(18);
            hisInpatientMedicalRecord.setTargetId(hisInoutTwentyfourHours.getId());
            HisHospitalManage hisHospitalManage =hisHospitalManageService.selectByHospNumber(hisInoutTwentyfourHours.getHospitalnumber());
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
