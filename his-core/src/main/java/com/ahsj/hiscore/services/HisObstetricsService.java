package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisObstetrics;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/16
 * @Time 14:29
 **/
public interface HisObstetricsService {

    /**
     * @Description 产科入院护理新增和修改
     * @Params: [hisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 17:59
     **/
    Message saveOrUpdate(HisObstetrics hisObstetrics) throws Exception;

    /**
     * @Description 护士确认
     * @Params: [hisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 18:01
     **/
    Message affirm(HisObstetrics hisObstetrics) throws Exception;

    /**
     * @Description 根据住院编号查询
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/15
     * @Time 18:51
     **/
    HisObstetrics selectByNumber(String number) throws Exception;
}
