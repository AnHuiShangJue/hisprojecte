package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisNursingAssessmentMapper;
import com.ahsj.hiscore.entity.HisNursingAssessment;
import com.ahsj.hiscore.services.HisNursingAssessmentService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/15
 * @Time 17:25
 **/
@Service
public class HisNursingAssessmentServiceImpl implements HisNursingAssessmentService {

    @Autowired
    HisNursingAssessmentMapper hisNursingAssessmentMapper;
    /**
     * className HisNursingAssessmentServiceImpl
     *
     * @author dingli
     * @date 2019/9/15 17:25
     */

    /**
     * @Description 增加入院护理
     * @Params: [hisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 17:26
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisNursingAssessment hisNursingAssessment) throws Exception {
        HisNursingAssessment ha = hisNursingAssessmentMapper.selectByhisHospitalManageId(hisNursingAssessment.getHisHospitalManageId());
        if (EmptyUtil.Companion.isNullOrEmpty(ha)) {
            hisNursingAssessmentMapper.insert(hisNursingAssessment);
            return MessageUtil.createMessage(true, "提交成功！");
        } else {
            hisNursingAssessment.setId(ha.getId());
            hisNursingAssessmentMapper.updateByPrimaryKey(hisNursingAssessment);
            return MessageUtil.createMessage(true, "修改成功！");
        }
    }

    /**
     * @Description 护士确认
     * @Params: [hisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 18:10
     **/
    @Override
    @Transactional(readOnly = false)
    public Message affirm(HisNursingAssessment hisNursingAssessment) throws Exception {
        HisNursingAssessment ha = hisNursingAssessmentMapper.selectByhisHospitalManageId(hisNursingAssessment.getHisHospitalManageId());
        ha.setNurseId(hisNursingAssessment.getNurseId());
        hisNursingAssessmentMapper.updateByPrimaryKey(ha);
        return MessageUtil.createMessage(true, "确认成功！");
    }

    /**
     * @Description 住院编号查询入院护理
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/15
     * @Time 18:52
     **/
    @Override
    @Transactional(readOnly = true)
    public HisNursingAssessment selectByNumber(String number) {
        return CodeHelper.getInstance().setCodeValue(hisNursingAssessmentMapper.selectByNumber(number));
    }
}
