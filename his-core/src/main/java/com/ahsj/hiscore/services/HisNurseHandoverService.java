package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisNurseHandover;
import core.message.Message;

public interface HisNurseHandoverService {

    /**
     *@Description 保存交接表信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/15
     *@Time 10:48
    */

    Message save(HisNurseHandover hisNurseHandover) throws Exception;



}
