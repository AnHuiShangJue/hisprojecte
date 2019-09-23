package com.ahsj.hiscore.services;

import core.message.Message;

/**
 * @program: his
 * @description:his_daily_record
 * @author: chenzhicai
 * @create: 2019-07-20-16-17
 **/
public interface HisDailyRecordService {
    
    /**
     *@Description 开始结算
     *@Params []
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019-07-20
     *@Time 16:18
    **/
    Message startCalculate();
}
