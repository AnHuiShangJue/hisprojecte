package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.MedicinePurchasingPlan;
import core.entity.PageBean;
import core.message.Message;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MedicinePurchasingPlanService {
    /**
     *@Description 药品采购计划
     *@Params [medicinePurchasingPlan]
     *@return sun.plugin2.message.Message
     *@Author zhushixiang
     *@Date 2019-07-02
     *@Time 16:24
    **/
    Message saveOrUpdate(Long[] ids, Long[] numbers, String personInCharge, String expectedTime, Double[] prices)throws Exception;

    /**
     *@Description 根据采购计划编号显示此次采购的详细信息
     *@Params [pageBean]
     *@return core.entity.PageBean<HisConsumablesDetails>
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 13:09
    **/
    PageBean<MedicinePurchasingPlan> details(PageBean<MedicinePurchasingPlan> pageBean)throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 16:00
    **/
    Message delete(Long[] ids)throws Exception;

    /**
     *@Description 根据计划编号查找(pharmacyId as id  为适应动态表格)
     *@Params [planNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 20:18
    **/
    List<MedicinePurchasingPlan> selectByPlanNumber(String planNumber);

    /**
     *@Description 根据计划编号查找
     *@Params [planNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 13:45
    **/
    List<MedicinePurchasingPlan> selectByPlanNumberforList(String planNumber);

    /**
     *@Description 药品采购计划明细编辑后提交
     *@Params [model, request, ids, numbers, personInCharge, expectedTime, prices]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 9:22
     **/
    Message saveDetails(Long[] ids, Long[] numbers, Double[] prices,String planNumber)throws Exception;

    /**
     *@Description 根据planNumber删除所有其下的相同采购计划明细
     *@Params [planNumber]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-12
     *@Time 10:45
     **/
    void deleteByPlanNumber(String planNumber)throws Exception;
}
