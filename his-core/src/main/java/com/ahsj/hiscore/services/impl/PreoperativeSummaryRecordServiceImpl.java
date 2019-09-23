package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.PreoperativeSummaryRecordMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.PreoperativeSummaryRecord;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.PreoperativeSummaryRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PreoperativeSummaryRecordServiceImpl
 * <p>
 * Date:     2019/9/17 13:30
 *
 * @author XJP
 * @create 2019/9/17
 * @since 1.0.0
 */

@Service
public class PreoperativeSummaryRecordServiceImpl implements PreoperativeSummaryRecordService {

    @Autowired
    PreoperativeSummaryRecordMapper preoperativeSummaryRecordMapper;


    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return com.ahsj.hiscore.entity.PreoperativeSummaryRecord
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 13:50
     **/
    @Override
    @Transactional(readOnly = true)
    public PreoperativeSummaryRecord selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return new PreoperativeSummaryRecord();
        } else {
            PreoperativeSummaryRecord preoperativeSummaryRecord = preoperativeSummaryRecordMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(preoperativeSummaryRecord)) {
                return new PreoperativeSummaryRecord();
            } else {
                return preoperativeSummaryRecord;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 增添和修改术前记录信息
     * @Params [hisDiagnosisTreatment]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:20
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addOrUpdatePreoperativeSummaryRecord(PreoperativeSummaryRecord preoperativeSummaryRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(preoperativeSummaryRecord) || EmptyUtil.Companion.isNullOrEmpty(preoperativeSummaryRecord.getJoinDate()) || EmptyUtil.Companion.isNullOrEmpty(preoperativeSummaryRecord.getMedicalNumber())) {
            return MessageUtil.createMessage(false, "操作失败 ！！！");
        } else {
            if (EmptyUtil.Companion.isNullOrEmpty(preoperativeSummaryRecord.getId())) {
                preoperativeSummaryRecordMapper.insert(preoperativeSummaryRecord);

                //新增大病历记录
                HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
                hisInpatientMedicalRecord.setMedicalRecordType(12);
                hisInpatientMedicalRecord.setTargetId(preoperativeSummaryRecord.getId());
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(preoperativeSummaryRecord.getMedicalNumber());
                hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
                HisMedicalRecord hisMedicalRecord =hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
                hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
                hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

                return MessageUtil.createMessage(true, "术前记录信息新增成功 ！！！");
            } else {
                preoperativeSummaryRecordMapper.updateByPrimaryKeySelective(preoperativeSummaryRecord);
                return MessageUtil.createMessage(true, "术前记录信息修改成功 ！！！");
            }
        }
    }
}
