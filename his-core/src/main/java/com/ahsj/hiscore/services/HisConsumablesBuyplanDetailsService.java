package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisConsumablesBuyplanDetailsService {


    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @MethodName saveOrUpdate
     * @Params [ids, numbers, personInCharge, expectedTime, prices]
     * @Author XJP
     * @Date 2020/4/24
     * @Time 14:01
     **/
    Message saveOrUpdate(Long[] ids, Integer[] numbers, String personInCharge, String expectedTime, Double[] prices) throws Exception;

    /**
     *@Description
     *@MethodName selectByBuyplanId
     *@Params [buyplanId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:35
    **/
    List<HisConsumablesBuyplanDetails> selectByBuyplanCode(String buyplanId) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     * @Description
     * @MethodName details
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/24
     * @Time 15:00
     **/
    PageBean<HisConsumablesBuyplanDetails> details(PageBean<HisConsumablesBuyplanDetails> pageBean) throws Exception;

    /**
     *@Description
     *@MethodName saveDetails
     *@Params [ids, numbers, prices, buyplanId]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:55
    **/
    Message saveDetails(Long[] ids, Integer[] numbers, Double[] prices, String buyplanCode) throws Exception;

    /**
     *@Description 
     *@MethodName delete
     *@Params [ids]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/24
     *@Time 16:58
    **/
    Message delete(Long[] ids) throws Exception;


    /**
     *@Description 
     *@MethodName selectByBuyplanforList
     *@Params [id]
     *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplanDetails>
     *@Author XJP
     *@Date 2020/4/25
     *@Time 15:48
    **/
    List<HisConsumablesBuyplanDetails> selectByBuyplanforList(Long id) throws Exception;

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
