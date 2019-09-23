package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisDailyCourseMapper;
import com.ahsj.hiscore.entity.HisDailyCourse;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisDailyCourseService;
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
 * @Time 13:32
 **/
@Service
public class HisDailyCourseServiceImpl implements HisDailyCourseService {

    @Autowired
    HisDailyCourseMapper hisDailyCourseMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;
    /**
     * className HisDailyCourseServiceImpl
     *
     * @author dingli
     * @date 2019/9/17 13:32
     */

    /**
     * @Description 日常病程新增或修改
     * @Params: [hisDailyCourse]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 13:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisDailyCourse hisDailyCourse) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisDailyCourse.getId())) {//新增
            hisDailyCourseMapper.insert(hisDailyCourse);
            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(3);
            hisInpatientMedicalRecord.setTargetId(hisDailyCourse.getId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisDailyCourse.getHisHospitalManageId());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);
            return MessageUtil.createMessage(true, "新增成功！");
        } else {
            hisDailyCourseMapper.updateByPrimaryKey(hisDailyCourse);
            return MessageUtil.createMessage(true, "修改成功！");
        }
    }

    /**
     * @Description 日常病程签字
     * @Params: [hisDailyCourse]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 13:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message sign(HisDailyCourse hisDailyCourse) throws Exception {
        return null;
    }

    /**
     * @Description 获取日常病程信息
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisDailyCourse
     * @Date 2019/9/19
     * @Time 15:57
     **/
    @Override
    @Transactional(readOnly = true)
    public HisDailyCourse getDailyCourse(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisDailyCourseMapper.selectByPrimaryKey(id));
    }
}
