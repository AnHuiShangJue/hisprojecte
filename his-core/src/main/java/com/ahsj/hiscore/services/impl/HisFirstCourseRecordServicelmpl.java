package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisFirstCourseRecordMapper;
import com.ahsj.hiscore.entity.HisFirstCourseRecord;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisFirstCourseRecordService;
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
public class HisFirstCourseRecordServicelmpl implements HisFirstCourseRecordService {
    @Autowired
    HisFirstCourseRecordMapper hisFirstCourseRecordMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisFirstCourseRecord
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:13
     **/
    @Override
    @Transactional(readOnly = true)
    public HisFirstCourseRecord selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisFirstCourseRecordMapper.selectById(id));
    }

    /**
     *@Description 新增或更新
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:44
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisFirstCourseRecord hisFirstCourseRecord) throws Exception {
        //ID空新增
        if(EmptyUtil.Companion.isNullOrEmpty(hisFirstCourseRecord.getId())){
            //新增输血记录
            hisFirstCourseRecord.setIsSign(2);
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisFirstCourseRecord.getHospitalManageId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisFirstCourseRecord.setRecordId(hisMedicalRecord.getId());
            hisFirstCourseRecordMapper.insert(hisFirstCourseRecord);

            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(2);
            hisInpatientMedicalRecord.setTargetId(hisFirstCourseRecord.getId());
            hisInpatientMedicalRecord.setHospitalManageId(hisFirstCourseRecord.getHospitalManageId());
            hisInpatientMedicalRecord.setRecordId(hisFirstCourseRecord.getRecordId());
            hisInpatientMedicalRecord.setMustSignNumber(1);
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);
            return MessageUtil.createMessage(true,"新增日常病程记录成功（Successful daily illness record）");
        }
        //ID不为空 更新
        else {
            hisFirstCourseRecordMapper.updateByPrimaryKeySelective(hisFirstCourseRecord);
            return MessageUtil.createMessage(true,"更新日常病程记录成功（Update daily disease record successfully）");
        }
    }

    /**
     *@Description 签字
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:10
     **/
    @Override
    @Transactional(readOnly = false)
    public Message sign(Long id, Long loginUserId,String signName) throws Exception {
        HisFirstCourseRecord hisFirstCourseRecord = hisFirstCourseRecordMapper.selectByPrimaryKey(id);
        if(hisFirstCourseRecord.getIsSign() == 1){
            return MessageUtil.createMessage(false,"已签字成功，请勿重复签字（Signed successfully, please do not repeat the signature）");
        }
        hisFirstCourseRecord.setDoctorId(loginUserId);
        hisFirstCourseRecord.setIsSign(1);
        HisInpatientMedicalRecord check = new HisInpatientMedicalRecord();
        check.setTargetId(id);
        check.setMedicalRecordType(2);
        HisInpatientMedicalRecord hisInpatientMedicalRecord = hisInpatientMedicalRecordService.selectByTargetIdAndType(check);
        hisInpatientMedicalRecord.setActualSignNumber(1);
        hisInpatientMedicalRecord.setSignName(signName);
        hisInpatientMedicalRecordService.update(hisInpatientMedicalRecord);
        hisFirstCourseRecordMapper.updateByPrimaryKeySelective(hisFirstCourseRecord);
        return MessageUtil.createMessage(true,"签字成功（Signed successfully）");
    }
    
}
