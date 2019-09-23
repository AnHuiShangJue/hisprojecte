package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisConsumablesBuyplanService {


    /**
     * @return
     * @Description
     * @Params
     * @Author jin
     * @Date 2019/7/3
     * @Time 11:24
     */

    PageBean<HisConsumablesBuyplan> list(PageBean<HisConsumablesBuyplan> pageBean) throws Exception;

    Message Update(HisConsumablesBuyplan hisMedicinePurchasingPlanRecord) throws Exception;

    Message delete(Long[] ids) throws Exception;

    HisConsumablesBuyplan selectById(Long id);

    HisConsumablesBuyplan selectByBuyplanId(Long buyplanId);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 18:21
     **/
    List<HisConsumablesBuyplan> queryTranslateInfoByDate(HisConsumablesBuyplan hisConsumablesBuyplan) throws Exception;

   /**
    *@功能说明 
    *@Params []
    *@return java.util.List<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
    *@Author XJP
    *@Date 2019/8/11
    *@Time 16:57
   **/
    List<HisConsumablesBuyplan> queryAll()throws Exception;


}
