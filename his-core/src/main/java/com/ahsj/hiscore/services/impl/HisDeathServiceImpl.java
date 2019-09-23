package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisDeathMapper;
import com.ahsj.hiscore.dao.HisHospitalManageMapper;
import com.ahsj.hiscore.entity.HisDeath;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HisDeathService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
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
 * @Time 20:24
 **/
@Service
public class HisDeathServiceImpl implements HisDeathService {

    /**
     * className HisDeathServiceImpl
     *
     * @author dingli
     * @date 2019/9/14 20:24
     */
    @Autowired
    HisDeathMapper hisDeathMapper;

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    /**
     * @Description 查看死亡记录
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisDeath
     * @Date 2019/9/14
     * @Time 20:41
     **/
    @Override
    @Transactional(readOnly = true)
    public HisDeath selectByNumber(String number) throws Exception {
        return hisDeathMapper.selectByNumber(number);
    }

    /**
     * @Description 增加死亡记录
     * @Params: [hisDeath]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 20:41
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insert(HisDeath hisDeath) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageMapper.selectByPrimaryKey(hisDeath.getHisHospitalManageId());
        if (hisHospitalManage.getIsDischarged() == Constants.HIS_DOCTOR_TWO) {
            return MessageUtil.createMessage(false, "提交失败,病人已出院！");
        }
        if (hisHospitalManage.getIsDischarged() == Constants.HIS_DOCTOR_THERE) {
            return MessageUtil.createMessage(false, "提交失败,病人已宣布死亡！");
        }
        if (hisHospitalManage.getIsDischarged() == Constants.HIS_DOCTOR_ONE) {
            hisDeathMapper.insert(hisDeath);

            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(21);
            hisInpatientMedicalRecord.setTargetId(hisDeath.getId());
            hisInpatientMedicalRecord.setHospitalManageId(hisDeath.getHisHospitalManageId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);
            hisHospitalManage.setIsDischarged(Constants.HIS_DOCTOR_THERE);
            hisHospitalManageService.update(hisHospitalManage);
            return MessageUtil.createMessage(true, "提交成功！");
        } else {
            return MessageUtil.createMessage(false, "参数异常，提交失败！");
        }
    }

    /**
     * @Description 死亡记录确认
     * @Params: [hisDeath]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 20:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message update(HisDeath hisDeath) throws Exception {
        hisDeathMapper.updateByPrimaryKeySelective(hisDeath);
        return MessageUtil.createMessage(true, "确定成功！");

    }

    /**
     *@Description
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 15:33
    **/
    @Override
    @Transactional(readOnly = true)
    public HisDeath selectById(Long targetId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisDeathMapper.selectByPrimaryKey(targetId));
    }
}
