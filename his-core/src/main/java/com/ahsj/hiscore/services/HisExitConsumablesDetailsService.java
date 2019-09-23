package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisExitConsumablesDetails;
import core.entity.PageBean;
import core.message.Message;

import java.util.Date;
import java.util.List;

public interface HisExitConsumablesDetailsService {

    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/6/25
     * @Time 14:33
     */
    Message exitConsumablesSmart(Long[] ids, Integer[] numbers, String personInCharge, Date expectedTime) throws Exception;

    Message exitConsumables(Long[] ids, Integer[] numbers, Double[] prices, String personInCharge, Date expectedTime) throws Exception;

    PageBean<HisExitConsumablesDetails> list(PageBean<HisExitConsumablesDetails> pageBean) throws Exception;

    PageBean<HisExitConsumablesDetails> listByNumber(PageBean<HisExitConsumablesDetails> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [ids, numbers, personInCharge, expectedTime]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 10:24
     **/
    Message exitConsumablesSmarts(Long[] ids, Integer[] numbers, String personInCharge, Date expectedTime) throws Exception;

    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/8/17
     * @Time 18:25
     */
    List<HisExitConsumablesDetails> getExitConsumabes(PageBean<HisExitConsumablesDetails> pageBean) throws Exception;

    /**
     * @Description 药库盘点
     * @Params: [pageBean]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisExitConsumablesDetails>
     * @Date 2019/9/19
     * @Time 18:02
     **/
    PageBean<HisExitConsumablesDetails> consumableInventory(PageBean<HisExitConsumablesDetails> pageBean) throws Exception;
}
