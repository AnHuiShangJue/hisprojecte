package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisConsultationNoteMapper;
import com.ahsj.hiscore.entity.HisConsultationNote;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisConsultationNoteService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/17
 * @Time 15:55
 **/
@Service
public class HisConsultationNoteServiceImpl implements HisConsultationNoteService {

    /**
     * className HisConsultationNoteServiceImpl
     *
     * @author dingli
     * @date 2019/9/17 15:55
     */

    @Autowired
    HisConsultationNoteMapper hisConsultationNoteMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @Description 会诊记录的新增和修改
     * @Params: [hisConsultationNote]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 16:05
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisConsultationNote hisConsultationNote) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisConsultationNote.getId())) {
            hisConsultationNoteMapper.insert(hisConsultationNote);

            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(10);
            hisInpatientMedicalRecord.setTargetId(hisConsultationNote.getId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisConsultationNote.getHisHospitalManageId());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);
            return MessageUtil.createMessage(true, "新增成功！");
        } else {
            hisConsultationNoteMapper.updateByPrimaryKey(hisConsultationNote);
            return MessageUtil.createMessage(true, "修改成功！");
        }
    }

    /**
     * @Description 查询会诊记录
     * @Params: [hisConsultationNote]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisConsultationNote
     * @Date 2019/9/17
     * @Time 16:20
     **/
    @Override
    @Transactional(readOnly = true)
    public HisConsultationNote getHisConsultationNote(HisConsultationNote hisConsultationNote) throws Exception {
        return hisConsultationNoteMapper.selectByPrimaryKey(hisConsultationNote.getId());
    }
}
