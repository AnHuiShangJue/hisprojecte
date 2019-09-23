package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisInoutTwentyfourHours;
import core.message.Message;

public interface HisInoutTwentyfourHoursService {
    /**
     *@Description 保存24小时内出入院记录
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/14
     *@Time 16:46
    */

    Message save(HisInoutTwentyfourHours hisInoutTwentyfourHours) throws Exception;



}
