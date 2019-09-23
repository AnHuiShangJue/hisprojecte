package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import core.entity.PageBean;
import core.message.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HisEnterConsumablesDetailsService {

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/6/23
     *@Time 15:59
    */

//    Message enterConsumables(HisEnterConsumablesDetails hisConsumables) throws Exception;
    void saveOrUpdate(@NotNull List<HisEnterConsumablesDetails> hisMediEnterDetailsList) throws Exception;


    Message mediEnter(Long[] ids, Integer[] numbers, Double[] prices,Long buyplanId,String[] validityPeriods)throws Exception;


    PageBean<HisEnterConsumablesDetails> list(PageBean<HisEnterConsumablesDetails> pageBean)throws Exception;

    PageBean<HisEnterConsumablesDetails> onelist(Long consumablesId)throws Exception;


}
