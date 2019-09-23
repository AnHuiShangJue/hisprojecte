package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisHospitalManageMapper;
import com.ahsj.hiscore.dao.HisLeaveHospitalMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisLeaveHospital;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisLeaveHospitalService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/14
 * @Time 16:39
 **/
@Service
public class HisLeaveHospitalServiceImpl implements HisLeaveHospitalService {

    @Autowired
    HisLeaveHospitalMapper hisLeaveHospitalMapper;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;


/** className HisLeaveHospitalServiceImpl
 *@author dingli
 *@date 2019/9/14 16:39
 */

    /**
     * @Description 新增病人 出院
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/14
     * @Time 16:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insert(HisLeaveHospital record) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByPrimaryKey(record.getHisHospitalManageId());
        if (hisHospitalManage.getIsDischarged() == Constants.HIS_DOCTOR_TWO) {
            return MessageUtil.createMessage(false, "出院失败,病人已出院！");
        }
        if (hisHospitalManage.getIsDischarged() == Constants.HIS_DOCTOR_THERE) {
            return MessageUtil.createMessage(false, "提交失败,病人已宣布死亡！");
        }
        if (hisHospitalManage.getIsDischarged() == Constants.HIS_DOCTOR_ONE) {
            hisLeaveHospitalMapper.insert(record);

            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(19);
            hisInpatientMedicalRecord.setTargetId(record.getId());
            hisInpatientMedicalRecord.setHospitalManageId(record.getHisHospitalManageId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);


            hisHospitalManage.setIsDischarged(Constants.HIS_DOCTOR_TWO);
            hisHospitalManageService.update(hisHospitalManage);
            return MessageUtil.createMessage(true, "出院成功！");
        } else {
            return MessageUtil.createMessage(false, "参数异常，出院失败！");
        }
    }

    /**
     * @Description 根据住院管理id查询出院信息
     * @Params: [hisHospitalManageId]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisLeaveHospital
     * @Date 2019/9/14
     * @Time 17:38
     **/
    @Override
    @Transactional(readOnly = true)
    public HisLeaveHospital getHisLeaveHospital(String number) throws Exception {
        return hisLeaveHospitalMapper.selectByHisHospitalManageId(number);
    }

    /**
     * @Description 医师确定
     * @Params: [record]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 18:41
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(HisLeaveHospital record) {
        hisLeaveHospitalMapper.updateByPrimaryKeySelective(record);
        return MessageUtil.createMessage(true, "确定成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public HisLeaveHospital selectById(Long targetId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisLeaveHospitalMapper.selectByPrimaryKey(targetId));
    }
}
