package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.entity.HisTollRecord;
import core.message.Message;

/**
 * @program: his
 * @description:挂号支付服务
 * @author: chenzhicai
 * @create: 2019-06-28-13-57
 **/
public interface HisRegisteredpayService {
    
    /**
     *@Description 新增或更新
     *@Params [hisRegisteredpay]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019/6/28
     *@Time 2:04 PM
    **/
    Message saveOrUpdate(HisRegisteredpay hisRegisteredpay) throws Exception;
    /**
     *@Description
     * @param hisRegisteredpay
     *@Author: dingli
     *@return
     *@Date 2019/7/22
     *@Time 21:26
     **/
    HisRegisteredpay getPrice(HisRegisteredpay hisRegisteredpay)throws Exception;

}
