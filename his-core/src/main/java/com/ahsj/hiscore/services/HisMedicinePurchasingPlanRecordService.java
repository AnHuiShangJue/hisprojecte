package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord;
import core.entity.PageBean;
import core.message.Message;

public interface HisMedicinePurchasingPlanRecordService {

    /**
     *@Description list
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord>
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 11:06
    **/
    PageBean<HisMedicinePurchasingPlanRecord> list(PageBean<HisMedicinePurchasingPlanRecord> pageBean)throws Exception;

    /**
     *@Description 新增或更新
     *@Params [hisMedicinePurchasingPlanRecord]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 11:07
    **/
    Message saveOrUpdate(HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord) throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 11:08
    **/
    Message delete(Long[] ids) throws Exception;

    /**
     *@Description 根据ID查找
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:23
    **/
    HisMedicinePurchasingPlanRecord selectById(Long id) ;

    /**
     *@Description 根据planNumber查找
     *@Params [planNumber]
     *@return com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:24
    **/
    HisMedicinePurchasingPlanRecord selectByPlanNumber(String planNumber);



}
