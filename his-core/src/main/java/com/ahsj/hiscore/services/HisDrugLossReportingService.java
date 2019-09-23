package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisDrugLossReporting;
import core.entity.PageBean;
import core.message.Message;

public interface HisDrugLossReportingService {

    /**
     *@Description 药品报损
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 11:21
     **/
    Message lossDrug(HisDrugLossReporting hisDrugLossReporting)throws Exception;

    /**
     *@Description list查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisDrugLossReporting>
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 14:57
    **/
    PageBean<HisDrugLossReporting> list(PageBean<HisDrugLossReporting> pageBean)throws Exception;
    
    /**
     *@Description 根据ID查找报损信息
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisDrugLossReporting
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 21:05
    **/
    HisDrugLossReporting selectById(Long id);

    /**
     *@Description 药品报损审核通过
     *@Params [hisDrugLossReporting]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 21:12
    **/
    Message reviewSuccess(HisDrugLossReporting hisDrugLossReporting)throws Exception;

    /**
     *@Description 药品报损审核未通过
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-07-11
     *@Time 20:32
    **/
    Message reviewFail(HisDrugLossReporting hisDrugLossReporting)throws Exception;

    /**
     *@Description 药品报损定时任务
     *@Params []
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-08-02
     *@Time 10:48
    **/
    void startScanDrug()throws Exception;
}
