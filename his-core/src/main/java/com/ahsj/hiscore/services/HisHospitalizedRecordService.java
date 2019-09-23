package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisHospitalizedRecord;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/18
 * @Time 10:14
 **/
public interface HisHospitalizedRecordService {
/** className HisHospitalizedRecordService
 *@author dingli
 *@date 2019/9/18 10:14
 */

    /**
     * @Description 入院记录的新增和修改
     * @Params: [hisHospitalizedRecord]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/18
     * @Time 10:17
     **/
    Message saveOrUpdate(HisHospitalizedRecord hisHospitalizedRecord) throws Exception;
}
