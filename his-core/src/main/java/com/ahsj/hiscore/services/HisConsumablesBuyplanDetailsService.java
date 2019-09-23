package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisConsumablesBuyplanDetailsService {

    /**
     * @return
     * @Description 新增或更新
     * @Params
     * @Author jin
     * @Date 2019/7/4
     * @Time 12:58
     */
    Message saveOrUpdate(Long[] ids, Integer[] numbers, Long buyplanId, String personInCharge, String expectedTime, Double[] prices) throws Exception;

    List<HisConsumablesBuyplanDetails> selectByBuyplanId(Long buyplanId) throws Exception;

    PageBean<HisConsumablesBuyplanDetails> details(PageBean<HisConsumablesBuyplanDetails> pageBean) throws Exception;

    Message saveDetails(Long[] ids, Integer[] numbers, Double[] prices, Long buyplanId) throws Exception;

    Message delete(Long[] ids) throws Exception;


    List<HisConsumablesBuyplanDetails> selectByBuyplanforList(Long buyplanId) throws Exception;

    Message delete(Long buyplanId) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:18
     **/
    List<HisConsumablesBuyplanDetails> queryTranslateInfoByDate(HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 17:00
     **/
    List<HisConsumablesBuyplanDetails> queryAll() throws Exception;
}
