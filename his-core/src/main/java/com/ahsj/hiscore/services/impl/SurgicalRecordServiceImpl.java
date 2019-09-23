package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.SurgicalRecordMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.SurgicalRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SurgicalRecordServiceImpl
 * <p>
 * Date:     2019/9/17 16:08
 *
 * @author XJP
 * @create 2019/9/17
 * @since 1.0.0
 */

@Service
public class SurgicalRecordServiceImpl implements SurgicalRecordService {

    @Autowired
    SurgicalRecordMapper surgicalRecordMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return com.ahsj.hiscore.entity.SurgicalRecord
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:16
     **/
    @Override
    @Transactional(readOnly = true)
    public SurgicalRecord selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return new SurgicalRecord();
        } else {
            SurgicalRecord surgicalRecord = surgicalRecordMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(surgicalRecord)) {
                return new SurgicalRecord();
            } else {
                return surgicalRecord;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [surgicalRecord]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:16
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addOrUpdateSurgicalRecord(SurgicalRecord surgicalRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(surgicalRecord) || EmptyUtil.Companion.isNullOrEmpty(surgicalRecord.getJoinDate()) || EmptyUtil.Companion.isNullOrEmpty(surgicalRecord.getMedicalNumber())
                || EmptyUtil.Companion.isNullOrEmpty(surgicalRecord.getEndTime()) || EmptyUtil.Companion.isNullOrEmpty(surgicalRecord.getStartTime())) {
            return MessageUtil.createMessage(false, "操作失败 ！！！");
        } else {
            if (EmptyUtil.Companion.isNullOrEmpty(surgicalRecord.getId())) {
                surgicalRecordMapper.insert(surgicalRecord);

                //新增大病历记录
             HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
                hisInpatientMedicalRecord.setMedicalRecordType(14);
                hisInpatientMedicalRecord.setTargetId(surgicalRecord.getId());
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(surgicalRecord.getMedicalNumber());
                hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
                HisMedicalRecord hisMedicalRecord =hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
                hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
                hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

                return MessageUtil.createMessage(true, "手术记录信息新增成功 ！！！");
            } else {
                surgicalRecordMapper.updateByPrimaryKeySelective(surgicalRecord);
                return MessageUtil.createMessage(true, "手术记录信息修改成功 ！！！");
            }
        }
    }
}
