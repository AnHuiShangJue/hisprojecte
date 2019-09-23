package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.PostoperativeRecordMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.PostoperativeRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: PostoperativeRecordServiceImpl
 * <p>
 * Date:     2019/9/18 10:17
 *
 * @author XJP
 * @create 2019/9/18
 * @since 1.0.0
 */

@Service
public class PostoperativeRecordServiceImpl implements PostoperativeRecordService {

    @Autowired
    PostoperativeRecordMapper postoperativeRecordMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return com.ahsj.hiscore.entity.PostoperativeRecord
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/18
     * @Time 10:31
     **/
    @Override
    @Transactional(readOnly = true)
    public PostoperativeRecord selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return new PostoperativeRecord();
        } else {
            PostoperativeRecord postoperativeRecord = postoperativeRecordMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(postoperativeRecord)) {
                return new PostoperativeRecord();
            } else {
                return postoperativeRecord;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [postoperativeRecord]
     * @Author XJP
     * @Date 2019/9/18
     * @Time 10:32
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addOrUpdatePostoperativeRecord(PostoperativeRecord postoperativeRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(postoperativeRecord) || EmptyUtil.Companion.isNullOrEmpty(postoperativeRecord.getJoinDate())) {
            return MessageUtil.createMessage(false, "操作失败 ！！！");
        } else {
            if (EmptyUtil.Companion.isNullOrEmpty(postoperativeRecord.getId())) {
                postoperativeRecordMapper.insert(postoperativeRecord);

                //新增大病历记录
                HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
                hisInpatientMedicalRecord.setMedicalRecordType(15);
                hisInpatientMedicalRecord.setTargetId(postoperativeRecord.getId());
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(postoperativeRecord.getMedicalNumber());
                hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
                HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
                hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
                hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

                return MessageUtil.createMessage(true, "术后记录信息新增成功 ！！！");
            } else {
                postoperativeRecordMapper.updateByPrimaryKeySelective(postoperativeRecord);
                return MessageUtil.createMessage(true, "术后记录信息修改成功 ！！！");
            }
        }
    }
}
