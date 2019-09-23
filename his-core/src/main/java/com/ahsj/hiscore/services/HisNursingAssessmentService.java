package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisNursingAssessment;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/15
 * @Time 17:24
 **/
public interface HisNursingAssessmentService {
    /**
     * className HisNursingAssessmentService
     *
     * @author dingli
     * @date 2019/9/15 17:24
     */

    /**
     * @Description 入院护理新增和修改
     * @Params: [hisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 17:59
     **/
    Message saveOrUpdate(HisNursingAssessment hisNursingAssessment) throws Exception;

    /**
     * @Description 护士确认
     * @Params: [hisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 18:01
     **/
    Message affirm(HisNursingAssessment hisNursingAssessment) throws Exception;

    /**
     * @Description 根据住院编号查询
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/15
     * @Time 18:51
     **/
    HisNursingAssessment selectByNumber(String number);
}
