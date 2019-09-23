package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisNurseHandoverPatientInfo;
import core.message.Message;

import java.util.List;

public interface HisNurseHandoverPatientInfoService {

    /**
     *@Description 保存交接班表的病人信息及交接内容
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/15
     *@Time 11:01
    */
    Message save(List<HisNurseHandoverPatientInfo> hisNurseHandoverPatientInfoList) throws Exception;




}
