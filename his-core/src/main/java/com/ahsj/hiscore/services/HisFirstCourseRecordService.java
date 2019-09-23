package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisFirstCourseRecord;
import core.message.Message;

public interface HisFirstCourseRecordService {
    
    /**
     *@Description 新增或更新
     *@Params [hisFirstCourseRecord]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 8:35
    **/
    Message saveOrUpdate(HisFirstCourseRecord hisFirstCourseRecord)throws Exception;

    /**
     *@Description 签字
     *@Params [id, loginUserId]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 8:35
    **/
    Message sign(Long id, Long loginUserId,String signName)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisFirstCourseRecord
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 8:43
    **/
    HisFirstCourseRecord selectById(Long id)throws Exception;
}
