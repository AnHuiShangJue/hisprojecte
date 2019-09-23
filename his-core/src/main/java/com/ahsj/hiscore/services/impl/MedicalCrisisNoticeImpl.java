package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.MedicalCrisisNoticeMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.MedicalCrisisNotice;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.MedicalCrisisNoticeService;
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
 * @create: 2019-09-17-10-04
 **/
@Service
public class MedicalCrisisNoticeImpl implements MedicalCrisisNoticeService {

    @Autowired
    MedicalCrisisNoticeMapper medicalCrisisNoticeMapper;
    
    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;


        

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(MedicalCrisisNotice medicalCrisisNotice) throws Exception {
        if(EmptyUtil.Companion.isNullOrEmpty(medicalCrisisNotice.getId())){
            medicalCrisisNoticeMapper.insert(medicalCrisisNotice);
            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(7);
            hisInpatientMedicalRecord.setTargetId(medicalCrisisNotice.getId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(medicalCrisisNotice.getMedicalNumber());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

            return MessageUtil.createMessage(true,"创建成功！");
        }else{
            medicalCrisisNoticeMapper.updateByPrimaryKey(medicalCrisisNotice);
            return MessageUtil.createMessage(true,"修改成功！");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MedicalCrisisNotice selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(medicalCrisisNoticeMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional(readOnly = true)
    public MedicalCrisisNotice selectByHosNumber(String number) {
        return CodeHelper.getInstance().setCodeValue(medicalCrisisNoticeMapper.selectByHosNumber(number));
    }
}

    