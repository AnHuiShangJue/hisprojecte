package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMedicalOrder;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMedicalOrderService {
    /**
     *@Description 根据就诊记录编号查找医嘱及其详细数据
     *@Params [recordId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 13:36
    **/
    List<HisMedicalOrder> selectByRecordId(String recordId);

    /**
     *@Description 根据就诊记录编号查找医嘱及其详细数据
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalOrder>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 13:35
    **/
    PageBean<HisMedicalOrder> listByRecordId(PageBean<HisMedicalOrder> pageBean) throws Exception;

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
    HisMedicalOrder selectById(Long id) throws Exception;

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 21:17
     **/
    Message saveOrUpdate(HisMedicalOrder hisMedicalOrder)throws Exception;

    /**
     *@Description 根据医嘱编号查询
     *@Params [medicalOrderNumber]
     *@return com.ahsj.hiscore.entity.HisMedicalOrder
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:31
    **/
    HisMedicalOrder selectByNumber(String medicalOrderNumber)throws Exception;
}
