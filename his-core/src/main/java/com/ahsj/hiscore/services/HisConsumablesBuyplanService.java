package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisConsumablesBuyplan;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisConsumablesBuyplanService {


  /**
   *@Description 
   *@MethodName list
   *@Params [pageBean]
   *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisConsumablesBuyplan>
   *@Author XJP
   *@Date 2020/4/24
   *@Time 13:16
  **/
    PageBean<HisConsumablesBuyplan> list(PageBean<HisConsumablesBuyplan> pageBean) throws Exception;

    /**
     *@Description
     *@MethodName Update
     *@Params [hisMedicinePurchasingPlanRecord]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/24
     *@Time 15:14
    **/
    Message saveOrUpdate(HisConsumablesBuyplan hisMedicinePurchasingPlanRecord) throws Exception;

    /**
     *@Description
     *@MethodName delete
     *@Params [ids]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/24
     *@Time 17:22
    **/
    Message delete(Long[] ids) throws Exception;

    HisConsumablesBuyplan selectById(Long id);

    HisConsumablesBuyplan selectBybuyplanCode(String buyplanCode);

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
