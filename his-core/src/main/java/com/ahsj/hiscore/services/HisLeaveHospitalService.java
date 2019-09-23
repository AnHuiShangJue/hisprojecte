package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.entity.HisLeaveHospital;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/14
 * @Time 16:37
 **/
public interface HisLeaveHospitalService {
/** className HisLeaveHospitalService
 *@author dingli
 *@date 2019/9/14 16:37
 */


    /**
     * @Description 病人出院
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/9/14
     * @Time 16:38
     **/
    Message insert(HisLeaveHospital record) throws Exception;

    /**
     * @Description 获取住院信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisLeaveHospital
     * @Date 2019/9/14
     * @Time 17:20
     **/
    HisLeaveHospital getHisLeaveHospital(String number) throws Exception;

    /**
     * @Description 医师确定
     * @Params: [record]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 18:40
     **/
    Message updateByPrimaryKeySelective(HisLeaveHospital record);

    /**
     *@Description
     *@Params [targetId]
     *@return com.ahsj.hiscore.entity.HisLeaveHospital
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 14:44
    **/
    HisLeaveHospital selectById(Long targetId)throws Exception;
}
