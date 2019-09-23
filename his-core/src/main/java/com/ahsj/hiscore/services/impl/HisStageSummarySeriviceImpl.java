package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisStageSummaryMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisStageSummary;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisStageSummaryService;
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
 * @create: 2019-09-17-09-14
 **/
@Service
public class HisStageSummarySeriviceImpl implements HisStageSummaryService {


    @Autowired
    HisStageSummaryMapper hisStageSummaryMapper;
    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;
    
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisStageSummary hisStageSummary) throws Exception {
        if(EmptyUtil.Companion.isNullOrEmpty(hisStageSummary.getId())){
            hisStageSummaryMapper.insert(hisStageSummary);
            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(7);
            hisInpatientMedicalRecord.setTargetId(hisStageSummary.getId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hisStageSummary.getMedicalNumber());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

            return MessageUtil.createMessage(true,"创建成功！");
        }else{
            hisStageSummaryMapper.updateByPrimaryKey(hisStageSummary);
            return MessageUtil.createMessage(true,"修改成功！");
        }    }

    @Override
    @Transactional(readOnly = true)
    public HisStageSummary selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisStageSummaryMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional(readOnly = true)
    public HisStageSummary selectByHosNumber(String number) {
        return CodeHelper.getInstance().setCodeValue(hisStageSummaryMapper.selectByHosNumber(number));
    }
}

    