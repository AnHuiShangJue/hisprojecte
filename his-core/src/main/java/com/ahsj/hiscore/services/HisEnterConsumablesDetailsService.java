package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import core.entity.PageBean;
import core.message.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HisEnterConsumablesDetailsService {

    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/6/23
     * @Time 15:59
     */

    //    Message enterConsumables(HisEnterConsumablesDetails hisConsumables) throws Exception;
    void saveOrUpdate(@NotNull List<HisEnterConsumablesDetails> hisMediEnterDetailsList) throws Exception;

    /**
     * @return core.message.Message
     * @Description
     * @MethodName mediEnter
     * @Params [ids, numbers, prices, buyplanCode, validityPeriods]
     * @Author XJP
     * @Date 2020/4/25
     * @Time 13:13
     **/
    Message mediEnter(Long[] ids, Integer[] numbers, Double[] prices, String buyplanCode, String[] validityPeriods) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisEnterConsumablesDetails>
     * @Description
     * @MethodName list
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/27
     * @Time 10:36
     **/
    PageBean<HisEnterConsumablesDetails> list(PageBean<HisEnterConsumablesDetails> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisEnterConsumablesDetails>
     * @Description
     * @MethodName onelist
     * @Params [consumablesCode]
     * @Author XJP
     * @Date 2020/4/26
     * @Time 15:52
     **/
    PageBean<HisEnterConsumablesDetails> onelist(String consumablesCode) throws Exception;


    /**
     * @return com.ahsj.hiscore.entity.HisEnterConsumablesDetails
     * @Description
     * @MethodName selectByEnterConsumablesDetailsId
     * @Params [id]
     * @Author XJP
     * @Date 2020/4/26
     * @Time 16:19
     **/
    HisEnterConsumablesDetails selectByEnterConsumablesDetailsId(Long id) throws Exception;
}
