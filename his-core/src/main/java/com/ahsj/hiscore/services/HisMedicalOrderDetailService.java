package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedicalOrderDetail;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMedicalOrderDetailService {
    /**
     *@Description 根据医嘱编号查看医嘱明细
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 15:23
    **/
    PageBean<HisMedicalOrderDetail> listDetailByNumber(PageBean<HisMedicalOrderDetail> pageBean) throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 17:31
    **/
    Message delete(Long[] ids) throws Exception;
    
    /**
     *@Description 
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicalOrderDetail
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 20:21
    **/
    HisMedicalOrderDetail selectById(Long id) throws Exception;
    
    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 21:17
    **/
    Message saveOrUpdate(HisMedicalOrderDetail hisMedicalOrderDetail)throws Exception;

    /**
     *@Description 校对和核准
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 11:30
    */
    Message proofreading(Long[] ids,String recordId) throws Exception;
    Message isApproved(Long[] ids,String recordId) throws Exception;

    /**
     *@Description 添加医嘱模板
     *@Params [templateNumber, number]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 9:34
    **/
    Message addTemplate(String templateNumber, String number)throws Exception;


    /**
     *@Description 通过医嘱开药
     *@Params [drugsNumb, medicalOrderNumber]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:16
     **/
    Message addMedicine(Long[] ids, String medicalOrderNumber)throws Exception;

    /**
     *@Description 通过医嘱开项目
     *@Params [drugsNumb, medicalOrderNumber]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:16
     **/
    Message addProject(Long[] ids, String medicalOrderNumber)throws Exception;

    /**
     *@Description 停嘱
     *@Params [id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 14:05
    **/
    Message stopOrder(Long id, Long loginUser, String stopDate)throws Exception;

    /**
     *@Description 根据医嘱编号查看明细
     *@Params [number]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-09-21
     *@Time 17:54
    **/
    List<HisMedicalOrderDetail> selectByNumberAscAndNotStop(String number)throws Exception;

    /**
     *@Description 医嘱添加药品组合
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 9:22
    **/
    Message addCombinationMedicine(Long[] ids)throws Exception;

    /**
     *@Description 取消临时医嘱
     *@Params [id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 11:26
    **/
    Message cancleOrder(Long id, Long loginUser, String stopDate)throws Exception;

    /**
     *@Description 根据医嘱当时生成输液单(定时任务)
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 22:02
    **/
    void createInfusionByMedicalOrder()throws Exception;

    /**
     *@Description 为所有正在住院的病人根据他们的长期医嘱每天定时生成对应的药品与项目收费明细（长期医嘱医生只需开一天的量即可）
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-10-04
     *@Time 23:44
    **/
    void createChargeDetailsForMedicalOrder()throws Exception;

    //保存用药医嘱
    Message saveMedicineOrder(String[] drugsNumbs, Integer[] nums, String[] usages, String[] intervals, String[] startTimes, String orderNumber)throws Exception;

    //保存项目医嘱
    Message saveProjectOrder(String[] numbers, Integer[] nums, String[] startTimes, String orderNumber)throws Exception;


    //根据医嘱编号查询所有医嘱明细
    List<HisMedicalOrderDetail> selectByOrderNumber(String orderNumber)throws Exception;
}
